package by.training.epam.lab9.dao.impl;

import by.training.epam.lab9.bean.entity.Subject;
import by.training.epam.lab9.dao.ISubjectDAO;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexh on 26.10.2016.
 */
public class SubjectDAO implements ISubjectDAO {

    private final static String GET_SUBJECT_LIST = "SELECT * FROM Subject";
    private final static String ADD_SUBJECT = "INSERT INTO Subject (name) VALUES (?)";
    private final static String GET_SUBJECT_BY_NAME = "SELECT * FROM Subject WHERE name = ?";


    @Override
    public List<Subject> getSubjectList(ConnectionPool.Connection c) throws DAOException {
        List<Subject> result = new ArrayList<>();
        try (Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery(GET_SUBJECT_LIST);
            while (rs.next()){
                result.add(new Subject(rs.getLong("id"), rs.getString("name")));
            }
        }
        catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
        }
        return result;
    }


    @Override
    public void addSubject(Subject subject, ConnectionPool.Connection c) throws DAOException {
        try (PreparedStatement st = c.prepareStatement(ADD_SUBJECT, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, subject.getName());
            st.executeUpdate();

            ResultSet resultSet = st.getGeneratedKeys();
            resultSet.next();
            subject.setId(resultSet.getLong(1));
        }
        catch (SQLException ex){
            switch (ex.getErrorCode()){
                case 1406:  throw new DAOException("Subject name must be no more than 100 characters", ex);
                case 1062:  throw new DAOException("The subject has been", ex);
                default:    throw new DAOException(ex.getMessage(), ex);
            }
        }
    }


    @Override
    public Subject getSubject(String name, ConnectionPool.Connection c) throws DAOException {
        Subject subject = null;
        try (PreparedStatement st = c.prepareStatement(GET_SUBJECT_BY_NAME)) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                subject = new Subject(rs.getLong("id"), rs.getString("name"));
            }
        }
        catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
        }
        return subject;
    }
}
