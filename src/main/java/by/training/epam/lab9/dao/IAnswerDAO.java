package by.training.epam.lab9.dao;

import by.training.epam.lab9.bean.entity.Answer;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.pool.ConnectionPool;

import java.util.List;

/**
 * Created by alexh on 25.10.2016.
 */
public interface IAnswerDAO {

    List<Answer> getAnswerList(long questionID, ConnectionPool.Connection c) throws DAOException;
    void addAnswer(Answer answer, ConnectionPool.Connection c) throws DAOException;

}
