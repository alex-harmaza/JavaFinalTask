package by.training.epam.lab9.test;

import by.training.epam.lab9.bean.entity.Question;
import by.training.epam.lab9.dao.IQuestionDAO;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.impl.QuestionDAO;
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
public class QuestionDAOTest extends DAOTest {

    private final static String ADD_SUBJECT = "INSERT INTO Subject (id, name) VALUES (?, ?)";
    private final static String ADD_TEST = "INSERT INTO Test (id, name, errorThreshold, subjectID) VALUES (?, ?, ?, ?)";
    private final static String GET_QUESTION = "SELECT * FROM Question WHERE text = ?";
    private final static String DELETE_QUESTION = "DELETE FROM Question WHERE text = ?";
    private final static String DELETE_TEST = "DELETE FROM TEST WHERE id = ?";
    private final static String DELETE_SUBJECT = "DELETE FROM Subject WHERE id = ?";

    private final IQuestionDAO questionDAO = new QuestionDAO();


    @Test(priority = 0)
    public void addQuestion() throws SQLException, DAOException {
        XmlData xmlData = EntityStore.getInstance().getXmlData();
        ConnectionPool.Connection connection = getConnection();
        connection.setAutoCommit(false);

        try {
            try (PreparedStatement s1 = connection.prepareStatement(ADD_TEST)) {
                s1.setLong(1, xmlData.getTest().getId());
                s1.setString(2, xmlData.getTest().getName());
                s1.setByte(3, xmlData.getTest().getErrorThreshold());
                s1.setLong(4, xmlData.getTest().getSubjectID());


                try (PreparedStatement s2 = connection.prepareStatement(ADD_SUBJECT)) {
                    s2.setLong(1, xmlData.getSubject().getId());
                    s2.setString(2, xmlData.getSubject().getName());
                    s2.executeUpdate();
                }

                s1.executeUpdate();
            }

            connection.commit();
        }
        catch (SQLException ex){
            connection.rollback();
            throw ex;
        }

        connection.setAutoCommit(true);
        Question question = XmlEntityConverter.toQuestion(xmlData.getQuestion());
        questionDAO.addQuestion(question, connection);

        List<Question> questionList = new ArrayList<>();
        try (PreparedStatement st = getConnection().prepareStatement(GET_QUESTION)) {
            st.setString(1, question.getText());
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()){
                Question foundedQuestion = new Question();
                foundedQuestion.setText(resultSet.getString("text"));
                foundedQuestion.setTestID(resultSet.getLong("testID"));
                foundedQuestion.setId(resultSet.getLong("id"));
                questionList.add(foundedQuestion);
            }
        }

        assertFalse(questionList.size() != 1, "Incorrect number of questions found");
        Question foundedQuestion = questionList.get(0);
        assertTrue(question.getText().equals(foundedQuestion.getText())
                && question.getTestID().equals(foundedQuestion.getTestID()), "Incorrect founded question");
    }


    @Test(priority = 1)
    public void getQuestionListTest() throws DAOException{
        Question question = XmlEntityConverter
                .toQuestion(EntityStore.getInstance().getXmlData().getQuestion());
        List<Question> questionList = questionDAO
                .getQuestionList(question.getTestID(), getConnection());
        assertTrue(questionList.size() == 1, "Incorrect number of founded questions");
        Question foundedQuestion = questionList.get(0);
        assertTrue(question.getText().equals(foundedQuestion.getText())
                && question.getTestID().equals(foundedQuestion.getTestID()), "Incorrect founded question");
    }


    @AfterClass
    public void clearDatabase() throws SQLException{
        XmlData xmlData = EntityStore.getInstance().getXmlData();
        try (PreparedStatement s1 = getConnection().prepareStatement(DELETE_SUBJECT)) {
            s1.setLong(1, xmlData.getSubject().getId());

            try (PreparedStatement s2 = getConnection().prepareStatement(DELETE_TEST)) {
                s2.setLong(1, xmlData.getTest().getId());

                try (PreparedStatement s3 = getConnection().prepareStatement(DELETE_QUESTION)) {
                    s3.setString(1, xmlData.getQuestion().getText());
                    s3.executeUpdate();
                }

                s2.executeUpdate();
            }

            s1.executeUpdate();
        }
    }

}
