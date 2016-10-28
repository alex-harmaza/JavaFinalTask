package by.training.epam.lab9.dao.impl;

import by.training.epam.lab9.bean.entity.User;
import by.training.epam.lab9.bean.entity.UserRole;
import by.training.epam.lab9.dao.IUserDAO;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Aliaksandr_Harmaza on 10/25/2016.
 */
public class UserDAO implements IUserDAO {

    private final static String GET_USER_BY_LOGIN = "SELECT * FROM User WHERE login = ?";
    private final static String ADD_USER = "INSERT INTO User (login, password, role) VALUES (?, ?, ?)";


    @Override
    public User getUser(String login, ConnectionPool.Connection c) throws DAOException {
        User user = null;
        try (PreparedStatement s = c.prepareStatement(GET_USER_BY_LOGIN)) {
            s.setString(1, login);
            ResultSet set = s.executeQuery();
            while (set.next()){
                user = new User();
                user.setId(set.getLong("id"));
                user.setLogin(set.getString("login"));
                user.setPassword(set.getString("password"));
                user.setRole(UserRole.valueOf(set.getString("role")));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return user;
    }


    @Override
    public void addUser(User user, ConnectionPool.Connection c) throws DAOException {
        try (PreparedStatement s = c.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());
            s.setString(3, user.getRole().name());
            s.executeUpdate();

            ResultSet resultSet = s.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getLong(1));
        } catch (SQLException ex) {
            switch (ex.getErrorCode()){
                case 1406:  throw new DAOException("Login must be no more than 45 characters", ex);
                case 1062:  throw new DAOException(String.format("User with login '%s' already exists", user.getLogin()), ex);
                default:    throw new DAOException(ex.getMessage(), ex);
            }
        }
    }

}
