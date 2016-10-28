package by.training.epam.lab9.test;

import by.training.epam.lab9.bean.entity.Subject;
import by.training.epam.lab9.bean.entity.Test;
import by.training.epam.lab9.dao.ITestDAO;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.impl.TestDAO;
import by.training.epam.lab9.entity.XmlData;
import by.training.epam.lab9.store.EntityStore;
import by.training.epam.lab9.utility.XmlEntityConverter;
import org.testng.annotations.AfterClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexh on 27.10.2016.
 */
public class TestDAOTest extends DAOTest {

    private final static String ADD_SUBJECT = "INSERT INTO Subject (id, name) VALUES (?, ?)";
    private final static String DELETE_SUBJECT = "DELETE FROM Subject WHERE id = ?";
    private final static String DELETE_TEST = "DELETE FROM Test WHERE name = ?";
    private final static String GET_TEST = "SELECT * FROM Test WHERE name = ?";

    private final ITestDAO testDAO = new TestDAO();


    @org.testng.annotations.Test(priority = 0)
    public void addTest() throws SQLException, DAOException {
        XmlData xmlData = EntityStore.getInstance().getXmlData();
        try (PreparedStatement st = getConnection().prepareStatement(ADD_SUBJECT)) {
            st.setLong(1, xmlData.getSubject().getId());
            st.setString(2, xmlData.getSubject().getName());
            st.executeUpdate();
        }

        Test test = XmlEntityConverter.toTest(xmlData.getTest());
        testDAO.addTest(test, getConnection());

        List<Test> testList = new ArrayList<>();
        try (PreparedStatement st = getConnection().prepareStatement(GET_TEST)) {
            st.setString(1, xmlData.getTest().getName());
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()){
                Test foundedTest = new Test();
                foundedTest.setId(resultSet.getLong("id"));
                foundedTest.setName(resultSet.getString("name"));
                foundedTest.setErrorThreshold(resultSet.getByte("errorThreshold"));
                foundedTest.setSubject(new Subject(resultSet.getLong("subjectID"), null));
                testList.add(foundedTest);
            }
        }

        assertFalse(testList.size() != 1, "Incorrect number of tests found");
        Test foundedTest = testList.get(0);
        assertTrue(test.getName().equals(foundedTest.getName())
                && test.getSubject().getId().equals(foundedTest.getSubject().getId())
                && test.getErrorThreshold().equals(foundedTest.getErrorThreshold()), "Incorrect founded test");
    }


    @org.testng.annotations.Test(priority = 1)
    public void setGetTest() throws DAOException {
        List<Test> testList = testDAO.getTestList(getConnection());
        assertTrue(testList.size() == 1, "Incorrect number of founded tests");
        Test test = XmlEntityConverter.toTest(EntityStore.getInstance().getXmlData().getTest());
        Test foundedTest = testList.get(0);
        assertTrue(test.getName().equals(foundedTest.getName())
                && test.getSubject().getId().equals(foundedTest.getSubject().getId())
                && test.getErrorThreshold().equals(foundedTest.getErrorThreshold()), "Incorrect founded test");
    }


    @AfterClass
    public void clearDatabase() throws SQLException{
        XmlData xmlData = EntityStore.getInstance().getXmlData();
        try (PreparedStatement s1 = getConnection().prepareStatement(DELETE_SUBJECT)) {
            s1.setLong(1, xmlData.getSubject().getId());

            try (PreparedStatement s2 = getConnection().prepareStatement(DELETE_TEST)) {
                s2.setString(1, xmlData.getTest().getName());
                s2.executeUpdate();
            }

            s1.executeUpdate();
        }
    }

}
