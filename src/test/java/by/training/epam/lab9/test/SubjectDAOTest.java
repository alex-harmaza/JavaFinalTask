package by.training.epam.lab9.test;

import by.training.epam.lab9.bean.entity.Subject;
import by.training.epam.lab9.dao.ISubjectDAO;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.impl.SubjectDAO;
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
public class SubjectDAOTest extends DAOTest {

    private final static String DELETE_SUBJECT = "DELETE FROM Subject WHERE name = ?";
    private final static String GET_SUBJECT_BY_NAME = "SELECT * FROM Subject WHERE name = ?";

    private final ISubjectDAO subjectDAO = new SubjectDAO();


    @Test(priority = 0)
    public void addSubjectTest() throws DAOException, SQLException {
        Subject subject = XmlEntityConverter
                .toSubject(EntityStore.getInstance().getXmlData().getSubject());
        subjectDAO.addSubject(subject, getConnection());

        List<Subject> subjectList = new ArrayList<>();
        try (PreparedStatement s = getConnection().prepareStatement(GET_SUBJECT_BY_NAME)) {
            s.setString(1, subject.getName());
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()){
                Subject foundedSubject = new Subject(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
                subjectList.add(foundedSubject);
            }
        }

        assertFalse(subjectList.size() != 1, "Incorrect number of subjects found");
        Subject foundedSubject = subjectList.get(0);
        assertTrue(subject.getName().equals(foundedSubject.getName()), "Incorrect founded subject");
    }


    @Test(priority = 1)
    public void getSubjectListTest() throws DAOException{
        assertTrue(subjectDAO.getSubjectList(getConnection()).size() == 1,
                "Incorrect number of subjects found");
    }


    @Test(priority = 1)
    public void getSubjectTest() throws DAOException{
        Subject subject = XmlEntityConverter
                .toSubject(EntityStore.getInstance().getXmlData().getSubject());
        Subject foundedSubject = subjectDAO.getSubject(subject.getName(), getConnection());
        assertTrue(subject.getName().equals(foundedSubject.getName()), "Incorrect founded subject");
    }


    @AfterClass
    public void clearDatabase() throws SQLException{
        try (PreparedStatement s = getConnection().prepareStatement(DELETE_SUBJECT)) {
            s.setString(1, EntityStore.getInstance().getXmlData().getSubject().getName());
            s.executeUpdate();
        }
    }

}
