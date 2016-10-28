package by.training.epam.lab9.dao;

import by.training.epam.lab9.bean.entity.Subject;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.pool.ConnectionPool;

import java.util.List;

/**
 * Created by alexh on 26.10.2016.
 */
public interface ISubjectDAO {

    List<Subject> getSubjectList(ConnectionPool.Connection c) throws DAOException;
    void addSubject(Subject subject, ConnectionPool.Connection c) throws DAOException;
    Subject getSubject(String name, ConnectionPool.Connection c) throws DAOException;

}
