package by.training.epam.lab9.test;

import by.training.epam.lab9.bean.entity.Answer;
import by.training.epam.lab9.dao.IAnswerDAO;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.impl.AnswerDAO;
import by.training.epam.lab9.dao.pool.ConnectionPool;
import by.training.epam.lab9.entity.XmlData;
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
public class AnswerDAOTest extends DAOTest {

    private final static String ADD_SUBJECT = "INSERT INTO Subject (id, name) VALUES (?, ?)";
    private final static String ADD_TEST = "INSERT INTO Test (id, name, errorThreshold, subjectID) VALUES (?, ?, ?, ?)";
    private final static String ADD_QUESTION = "INSERT INTO Question (id, text, testID) VALUES (?, ?, ?)";
    private final static String GET_ANSWER = "SELECT * FROM Answer WHERE text = ?";
    private final static String DELETE_ANSWER = "DELETE FROM Answer WHERE text = ?";
    private final static String DELETE_QUESTION = "DELETE FROM Question WHERE id = ?";
    private final static String DELETE_TEST = "DELETE FROM TEST WHERE id = ?";
    private final static String DELETE_SUBJECT = "DELETE FROM Subject WHERE id = ?";

    private final IAnswerDAO answerDAO = new AnswerDAO();


    @Test(priority = 0)
    public void addAnswer() throws SQLException, DAOException {
        XmlData xmlData = EntityStore.getInstance().getXmlData();
        ConnectionPool.Connection connection = getConnection();

        try (PreparedStatement s1 = connection.prepareStatement(ADD_QUESTION)) {
            s1.setLong(1, xmlData.getQuestion().getId());
            s1.setString(2, xmlData.getQuestion().getText());
            s1.setLong(3, xmlData.getQuestion().getTestID());

            try (PreparedStatement s2 = connection.prepareStatement(ADD_TEST)) {
                s2.setLong(1, xmlData.getTest().getId());
                s2.setString(2, xmlData.getTest().getName());
                s2.setByte(3, xmlData.getTest().getErrorThreshold());
                s2.setLong(4, xmlData.getTest().getSubjectID());

                try (PreparedStatement s3 = connection.prepareStatement(ADD_SUBJECT)) {
                    s3.setLong(1, xmlData.getSubject().getId());
                    s3.setString(2, xmlData.getSubject().getName());
                    s3.executeUpdate();
                }

                s2.executeUpdate();
            }

            s1.executeUpdate();
        }

        Answer answer = XmlEntityConverter.toAnswer(xmlData.getAnswer());
        answerDAO.addAnswer(answer, connection);

        List<Answer> answerList = new ArrayList<>();
        try (PreparedStatement st = getConnection().prepareStatement(GET_ANSWER)) {
            st.setString(1, answer.getText());
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()){
                Answer foundedAnswer = new Answer();
                foundedAnswer.setText(resultSet.getString("text"));
                foundedAnswer.setCorrect(resultSet.getBoolean("isCorrect"));
                foundedAnswer.setQuestionID(resultSet.getLong("questionID"));
                answerList.add(foundedAnswer);
            }
        }

        assertFalse(answerList.size() != 1, "Incorrect number of answers found");
        Answer foundedAnswer = answerList.get(0);
        assertTrue(answer.getCorrect().equals(foundedAnswer.getCorrect())
                && answer.getText().equals(foundedAnswer.getText())
                && answer.getQuestionID().equals(foundedAnswer.getQuestionID()), "Incorrect founded answer");
    }


    @Test(priority = 1)
    public void getAnswerListTest() throws DAOException{
        Answer answer = XmlEntityConverter
                .toAnswer(EntityStore.getInstance().getXmlData().getAnswer());
        List<Answer> answerList = answerDAO
                .getAnswerList(answer.getQuestionID(), getConnection());
        assertTrue(answerList.size() == 1, "Incorrect number of founded answers");
        Answer foundedAnswer = answerList.get(0);
        assertTrue(answer.getCorrect().equals(foundedAnswer.getCorrect())
                && answer.getText().equals(foundedAnswer.getText())
                && answer.getQuestionID().equals(foundedAnswer.getQuestionID()), "Incorrect founded answer");
    }


    @AfterClass
    public void clearDatabase() throws SQLException{
        XmlData xmlData = EntityStore.getInstance().getXmlData();
        try (PreparedStatement s1 = getConnection().prepareStatement(DELETE_SUBJECT)) {
            s1.setLong(1, xmlData.getSubject().getId());

            try (PreparedStatement s2 = getConnection().prepareStatement(DELETE_TEST)) {
                s2.setLong(1, xmlData.getTest().getId());

                try (PreparedStatement s3 = getConnection().prepareStatement(DELETE_QUESTION)) {
                    s3.setLong(1, xmlData.getQuestion().getId());

                    try (PreparedStatement s4 = getConnection().prepareStatement(DELETE_ANSWER)) {
                        s4.setString(1, xmlData.getAnswer().getText());
                        s4.executeUpdate();
                    }

                    s3.executeUpdate();
                }

                s2.executeUpdate();
            }

            s1.executeUpdate();
        }
    }

}
