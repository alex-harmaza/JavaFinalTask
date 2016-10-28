package by.training.epam.lab9.test;

import by.training.epam.lab9.bean.entity.User;
import by.training.epam.lab9.bean.entity.UserRole;
import by.training.epam.lab9.dao.IUserDAO;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.impl.UserDAO;
import by.training.epam.lab9.store.EntityStore;
import by.training.epam.lab9.utility.XmlEntityConverter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexh on 27.10.2016.
 */
public class UserDAOTest extends DAOTest {

    private final static String DELETE_USER = "DELETE FROM User WHERE login = ?";
    private final static String GET_USER_BY_LOGIN = "SELECT * FROM User WHERE login = ?";
    private final IUserDAO userDAO = new UserDAO();


    @Test(priority = 0)
    public void addUserTest() throws DAOException, SQLException {
        User user = XmlEntityConverter
                .toUser(EntityStore.getInstance().getXmlData().getUser());
        userDAO.addUser(user, getConnection());

        List<User> userList = new ArrayList<>();
        try (PreparedStatement s = getConnection().prepareStatement(GET_USER_BY_LOGIN)) {
            s.setString(1, user.getLogin());
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()){
                User foundedUser = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        UserRole.valueOf(resultSet.getString("role"))
                );
                userList.add(foundedUser);
            }
        }

        assertFalse(userList.size() != 1, "Incorrect number of users found");
        User foundedUser = userList.get(0);
        assertTrue(user.getLogin().equals(foundedUser.getLogin())
                && user.getPassword().equals(foundedUser.getPassword())
                && user.getRole().equals(foundedUser.getRole()), "Incorrect founded user");
    }


    @Test(priority = 1)
    public void getUserByLoginTest() throws DAOException {
        User user = XmlEntityConverter
                .toUser(EntityStore.getInstance().getXmlData().getUser());
        User foundedUser = userDAO.getUser(user.getLogin(), getConnection());
        assertTrue(user.getLogin().equals(foundedUser.getLogin())
                && user.getPassword().equals(foundedUser.getPassword())
                && user.getRole().equals(foundedUser.getRole()), "Incorrect founded user");
    }


    @AfterClass
    public void clearDatabase() throws SQLException{
        try (PreparedStatement s = getConnection().prepareStatement(DELETE_USER)) {
            s.setString(1, EntityStore.getInstance().getXmlData().getUser().getLogin());
            s.executeUpdate();
        }
    }
}
