package by.training.epam.lab9.dao.factory;

import by.training.epam.lab9.dao.*;
import by.training.epam.lab9.dao.impl.*;

/**
 * Created by alexh on 26.10.2016.
 */
public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();

    private final ITestDAO testDAO = new TestDAO();
    private final IQuestionDAO questionDAO = new QuestionDAO();
    private final IAnswerDAO answerDAO = new AnswerDAO();
    private final IUserDAO userDAO = new UserDAO();
    private final ISubjectDAO subjectDAO = new SubjectDAO();


    public static DAOFactory getInstance(){
        return instance;
    }


    private DAOFactory(){}


    public ITestDAO getTestDAO() {
        return testDAO;
    }

    public IQuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    public IAnswerDAO getAnswerDAO() {
        return answerDAO;
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public ISubjectDAO getSubjectDAO() {
        return subjectDAO;
    }
}
