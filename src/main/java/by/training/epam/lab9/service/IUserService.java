package by.training.epam.lab9.service;

import by.training.epam.lab9.service.exception.ServiceException;

/**
 * Created by alexh on 26.10.2016.
 */
public interface IUserService {

    boolean login(String login, String password) throws ServiceException;
    void register(String login, String password) throws ServiceException;

}
