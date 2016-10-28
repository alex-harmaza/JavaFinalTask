package by.training.epam.lab9.service;

import by.training.epam.lab9.bean.entity.Subject;
import by.training.epam.lab9.service.exception.ServiceException;

import java.util.List;

/**
 * Created by alexh on 27.10.2016.
 */
public interface ISubjectService {

    List<Subject> getSubjectList() throws ServiceException;
    void addSubject(String subject) throws ServiceException;

}
