package by.training.epam.lab9.service;

import by.training.epam.lab9.bean.dto.TestDTO;
import by.training.epam.lab9.bean.entity.Test;
import by.training.epam.lab9.service.exception.ServiceException;

import java.util.List;

/**
 * Created by alexh on 25.10.2016.
 */
public interface ITestService {

    void addTest(TestDTO testDTO) throws ServiceException;
    List<Test> getTestList() throws ServiceException;
    TestDTO getTest(long testID) throws ServiceException;

}
