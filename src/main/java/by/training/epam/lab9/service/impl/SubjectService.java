package by.training.epam.lab9.service.impl;

import by.training.epam.lab9.bean.entity.Subject;
import by.training.epam.lab9.bean.entity.UserRole;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.dao.factory.DAOFactory;
import by.training.epam.lab9.dao.pool.ConnectionPool;
import by.training.epam.lab9.service.ISubjectService;
import by.training.epam.lab9.service.exception.ServiceException;
import by.training.epam.lab9.service.impl.context.UserContext;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by alexh on 27.10.2016.
 */
public class SubjectService implements ISubjectService {

    @Override
    public List<Subject> getSubjectList() throws ServiceException {
        if (UserContext.getInstance().getRole() != UserRole.TUTOR){
            throw new ServiceException("Permission denied or not authorized");
        }
        List<Subject> result;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection connection = pool.takeConnection();
            try {
                result = DAOFactory.getInstance().getSubjectDAO()
                        .getSubjectList(connection);
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
    public void addSubject(String subject) throws ServiceException {
        if (subject == null){
            throw new ServiceException("Incorrect subject");
        }

        if (UserContext.getInstance().getRole() != UserRole.TUTOR){
            throw new ServiceException("Permission denied or not authorized");
        }

        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection connection = pool.takeConnection();
            try {
                DAOFactory.getInstance().getSubjectDAO()
                        .addSubject(new Subject(null, subject), connection);
            }
            finally {
                pool.returnConnection(connection);
            }
        }
        catch (InterruptedException | SQLException | DAOException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
}
