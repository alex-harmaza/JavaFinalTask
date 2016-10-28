package by.training.epam.lab9.dao;

import by.training.epam.lab9.bean.entity.Test;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.pool.ConnectionPool;

import java.util.List;

/**
 * Created by Aliaksandr_Harmaza on 10/25/2016.
 */
public interface ITestDAO {

    void addTest(Test test, ConnectionPool.Connection c) throws DAOException;
    Test getTest(long id, ConnectionPool.Connection c) throws DAOException;
    List<Test> getTestList(ConnectionPool.Connection c) throws DAOException;

}
