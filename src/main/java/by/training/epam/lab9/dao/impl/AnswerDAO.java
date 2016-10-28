package by.training.epam.lab9.dao.impl;

import by.training.epam.lab9.bean.entity.Answer;
import by.training.epam.lab9.dao.IAnswerDAO;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexh on 25.10.2016.
 */
public class AnswerDAO implements IAnswerDAO {

    private final static String GET_ANSWER_LIST = "SELECT * FROM Answer WHERE questionID = ?";
    private final static String ADD_ANSWER = "INSERT INTO Answer (text, questionID, isCorrect) VALUES (?, ?, ?)";


    @Override
    public List<Answer> getAnswerList(long questionID, ConnectionPool.Connection c) throws DAOException {
        List<Answer> list = new ArrayList<>();
        try (PreparedStatement s = c.prepareStatement(GET_ANSWER_LIST)) {
            s.setLong(1, questionID);
            ResultSet set = s.executeQuery();
            while (set.next()){
                Answer answer = new Answer();
                answer.setCorrect(set.getBoolean("isCorrect"));
                answer.setQuestionID(set.getLong("questionID"));
                answer.setText(set.getString("text"));
                list.add(answer);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return list;
    }


    @Override
    public void addAnswer(Answer answer, ConnectionPool.Connection c) throws DAOException {
        try (PreparedStatement s = c.prepareStatement(ADD_ANSWER)) {
            s.setString(1, answer.getText());
            s.setLong(2, answer.getQuestionID());
            s.setBoolean(3, answer.getCorrect());
            s.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

}
