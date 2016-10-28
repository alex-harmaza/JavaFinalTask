package by.training.epam.lab9.service.impl;

import by.training.epam.lab9.bean.entity.User;
import by.training.epam.lab9.bean.entity.UserRole;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.factory.DAOFactory;
import by.training.epam.lab9.dao.pool.ConnectionPool;
import by.training.epam.lab9.service.IUserService;
import by.training.epam.lab9.service.exception.ServiceException;
import by.training.epam.lab9.service.impl.context.UserContext;
import by.training.epam.lab9.service.impl.utility.SHA256;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by alexh on 26.10.2016.
 */
public class UserService implements IUserService {

    @Override
    public boolean login(String login, String password) throws ServiceException {
        if (login == null){
            throw new ServiceException("Incorrect login");
        }
        if (password == null){
            throw new ServiceException("Incorrect password");
        }

        boolean result = false;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection connection = pool.takeConnection();
            try {
                User user = DAOFactory.getInstance().getUserDAO().getUser(login, connection);
                if (user != null && user.getPassword().equals(SHA256.convert(password))){
                    UserContext.getInstance().setRole(user.getRole());
                    result = true;
                }
            }
            finally {
                pool.returnConnection(connection);
            }
        }
        catch (DAOException | InterruptedException | NoSuchAlgorithmException
                | UnsupportedEncodingException | SQLException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
        return result;
    }


    @Override
    public void register(String login, String password) throws ServiceException {
        if (login == null){
            throw new ServiceException("Incorrect login");
        }
        if (password == null){
            throw new ServiceException("Incorrect password");
        }

        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection connection = pool.takeConnection();
            try {
                DAOFactory.getInstance().getUserDAO()
                        .addUser(new User(login, SHA256.convert(password), UserRole.STUDENT), connection);
            }
            finally {
                pool.returnConnection(connection);
            }
        }
        catch (InterruptedException | SQLException | NoSuchAlgorithmException
                | UnsupportedEncodingException |DAOException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

}
