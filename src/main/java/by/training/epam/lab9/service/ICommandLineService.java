package by.training.epam.lab9.service;

import by.training.epam.lab9.service.exception.ServiceException;

/**
 * Created by alexh on 27.10.2016.
 */
public interface ICommandLineService {

    String showHelp() throws ServiceException;

}
