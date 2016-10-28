package by.training.epam.lab9.dao;

import by.training.epam.lab9.bean.entity.User;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.pool.ConnectionPool;

/**
 * Created by Aliaksandr_Harmaza on 10/25/2016.
 */
public interface IUserDAO {

    User getUser(String login, ConnectionPool.Connection c) throws DAOException;
    void addUser(User user, ConnectionPool.Connection c) throws DAOException;

}
