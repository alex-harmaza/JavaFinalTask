package by.training.epam.lab9.dao;

import by.training.epam.lab9.bean.entity.Question;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.pool.ConnectionPool;

import java.util.List;

/**
 * Created by alexh on 25.10.2016.
 */
public interface IQuestionDAO {

    List<Question> getQuestionList(long testID, ConnectionPool.Connection c) throws DAOException;
    void addQuestion(Question question, ConnectionPool.Connection c) throws DAOException;

}
