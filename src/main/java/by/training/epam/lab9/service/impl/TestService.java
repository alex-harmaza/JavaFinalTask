package by.training.epam.lab9.service.impl;

import by.training.epam.lab9.bean.dto.AnswerDTO;
import by.training.epam.lab9.bean.dto.QuestionDTO;
import by.training.epam.lab9.bean.dto.TestDTO;
import by.training.epam.lab9.bean.entity.Answer;
import by.training.epam.lab9.bean.entity.Question;
import by.training.epam.lab9.bean.entity.Test;
import by.training.epam.lab9.bean.entity.UserRole;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.factory.DAOFactory;
import by.training.epam.lab9.dao.pool.ConnectionPool;
import by.training.epam.lab9.service.ITestService;
import by.training.epam.lab9.service.exception.ServiceException;
import by.training.epam.lab9.service.impl.context.UserContext;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by alexh on 26.10.2016.
 */
public class TestService implements ITestService {

    @Override
    public void addTest(TestDTO testDTO) throws ServiceException {
        if (testDTO == null){
            throw new ServiceException("Incorrect testDTO");
        }

        if (UserContext.getInstance().getRole() != UserRole.TUTOR){
            throw new ServiceException("Permission denied or not authorized");
        }

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection connection = pool.takeConnection();
            try {
                connection.setAutoCommit(false);

                Test addedTest = new Test();
                addedTest.setName(testDTO.getName());
                addedTest.setErrorThreshold(testDTO.getErrorThreshold());
                addedTest.setSubject(testDTO.getSubject().getId() == null
                        ? daoFactory.getSubjectDAO().getSubject(testDTO.getSubject().getName(), connection)
                        : testDTO.getSubject());

                if (addedTest.getSubject() == null){
                    throw new ServiceException("Subject not found");
                }

                daoFactory.getTestDAO().addTest(addedTest, connection);

                for (QuestionDTO questionDTO : testDTO.getQuestionSet()){
                    Question question = new Question();
                    question.setText(questionDTO.getText());
                    question.setTestID(addedTest.getId());
                    daoFactory.getQuestionDAO().addQuestion(question, connection);

                    for (AnswerDTO answerDTO : questionDTO.getAnswerList()){
                        Answer answer = new Answer();
                        answer.setCorrect(answerDTO.getCorrect());
                        answer.setText(answerDTO.getText());
                        answer.setQuestionID(question.getId());
                        daoFactory.getAnswerDAO().addAnswer(answer, connection);
                    }
                }

                connection.commit();
            }
            catch (SQLException | DAOException ex){
                connection.rollback();
                throw ex;
            }
            finally {
                pool.returnConnection(connection);
            }
        } catch (InterruptedException | SQLException | DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    @Override
    public List<Test> getTestList() throws ServiceException {
        if (UserContext.getInstance().getRole() == null){
            throw new ServiceException("You are not authorized");
        }
        List<Test> result;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection connection = pool.takeConnection();
            try {
                result = DAOFactory.getInstance().getTestDAO().getTestList(connection);
            }
            finally {
                pool.returnConnection(connection);
            }
        }
        catch (InterruptedException | SQLException | DAOException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
        return result;
    }


    @Override
    public TestDTO getTest(long testID) throws ServiceException {
        if (UserContext.getInstance().getRole() == null){
            throw new ServiceException("You are not authorized");
        }

        TestDTO result = null;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection connection = pool.takeConnection();
            try {
                Test foundedTest = daoFactory.getTestDAO().getTest(testID, connection);

                if (foundedTest == null){
                    throw new ServiceException("Test not found");
                }

                result = new TestDTO(foundedTest.getName(),
                        foundedTest.getSubject(), foundedTest.getErrorThreshold());

                for (Question question : daoFactory.getQuestionDAO().getQuestionList(testID, connection)){
                    QuestionDTO questionDTO = new QuestionDTO(question.getText());
                    List<Answer> answers = daoFactory.getAnswerDAO()
                            .getAnswerList(question.getId(), connection);
                    for (Answer answer : answers){
                        questionDTO.getAnswerList()
                                .add(new AnswerDTO(answer.getText(), answer.getCorrect()));
                    }
                    result.getQuestionSet().add(questionDTO);
                }
            }
            finally {
                pool.returnConnection(connection);
            }
        }
        catch (InterruptedException | SQLException | DAOException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
        return result;
    }


}
