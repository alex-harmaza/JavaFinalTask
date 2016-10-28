package by.training.epam.lab9.dao.impl;

import by.training.epam.lab9.bean.entity.Question;
import by.training.epam.lab9.dao.IQuestionDAO;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexh on 25.10.2016.
 */
public class QuestionDAO implements IQuestionDAO {

    private final static String GET_QUESTIONS = "SELECT * FROM Question where testID = ?";
    private final static String ADD_QUESTION = "INSERT INTO Question (text, testID) VALUES (?, ?)";


    @Override
    public List<Question> getQuestionList(long testID, ConnectionPool.Connection c) throws DAOException {
        List<Question> list = new ArrayList<>();
        try (PreparedStatement s = c.prepareStatement(GET_QUESTIONS)) {
            s.setLong(1, testID);
            ResultSet set = s.executeQuery();
            while (set.next()){
                Question question = new Question();
                question.setId(set.getLong("id"));
                question.setText(set.getString("text"));
                question.setTestID(set.getLong("testID"));
                list.add(question);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return list;
    }


    @Override
    public void addQuestion(Question question, ConnectionPool.Connection c) throws DAOException{
        try (PreparedStatement s = c.prepareStatement(ADD_QUESTION, Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, question.getText());
            s.setLong(2, question.getTestID());
            s.executeUpdate();

            ResultSet resultSet = s.getGeneratedKeys();
            resultSet.next();
            question.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

}
