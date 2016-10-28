package by.training.epam.lab9.dao.impl;

import by.training.epam.lab9.bean.entity.Subject;
import by.training.epam.lab9.bean.entity.Test;
import by.training.epam.lab9.dao.ITestDAO;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aliaksandr_Harmaza on 10/25/2016.
 */
public class TestDAO implements ITestDAO {

    private final static String GET_TEST = "SELECT Test.id, Test.name, Test.errorThreshold, Test.subjectID, "
            + "Subject.name as 'subjectName' FROM Test JOIN Subject ON Test.subjectID = Subject.id WHERE Test.id = ?";
    private final static String ADD_TEST = "INSERT INTO Test (name, subjectID, errorThreshold) VALUES (?, ?, ?)";
    private final static String GET_ALL_TESTS = "SELECT Test.id, Test.name, Test.errorThreshold, Test.subjectID, "
            + "Subject.name as 'subjectName' FROM Test JOIN Subject ON Test.subjectID = Subject.id";


    @Override
    public void addTest(Test test, ConnectionPool.Connection c) throws DAOException {
        try (PreparedStatement s = c.prepareStatement(ADD_TEST, Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, test.getName());
            s.setLong(2, test.getSubject().getId());
            s.setLong(3, test.getErrorThreshold());
            s.executeUpdate();

            ResultSet resultSet = s.getGeneratedKeys();
            resultSet.next();
            test.setId(resultSet.getLong(1));
        } catch (SQLException ex) {
            switch (ex.getErrorCode()){
                case 1406:  throw new DAOException("Test name must be no more than 100 characters", ex);
                case 1452:  throw new DAOException("Incorrect subject", ex);
                case 1062:  throw new DAOException("The test has been", ex);
                default:    throw new DAOException(ex.getMessage(), ex);
            }
        }
    }


    @Override
    public Test getTest(long id, ConnectionPool.Connection c) throws DAOException{
        Test test = null;
        try (PreparedStatement s = c.prepareStatement(GET_TEST)) {
            s.setLong(1, id);
            ResultSet set = s.executeQuery();
            while (set.next()){
                test = new Test();
                test.setId(set.getLong("id"));
                test.setName(set.getString("name"));
                test.setSubject(new Subject(set.getLong("subjectID"), set.getString("subjectName")));
                test.setErrorThreshold(set.getByte("errorThreshold"));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return test;
    }


    @Override
    public List<Test> getTestList(ConnectionPool.Connection c) throws DAOException {
        List<Test> tests = new ArrayList<>();
        try (Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(GET_ALL_TESTS);
            while (rs.next()){
                Test test = new Test();
                test.setId(rs.getLong("id"));
                test.setName(rs.getString("name"));
                test.setSubject(new Subject(rs.getLong("subjectID"), rs.getString("subjectName")));
                test.setErrorThreshold(rs.getByte("errorThreshold"));
                tests.add(test);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return tests;
    }

}
