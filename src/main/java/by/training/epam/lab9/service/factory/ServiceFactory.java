package by.training.epam.lab9.service.factory;

import by.training.epam.lab9.service.ICommandLineService;
import by.training.epam.lab9.service.ISubjectService;
import by.training.epam.lab9.service.ITestService;
import by.training.epam.lab9.service.IUserService;
import by.training.epam.lab9.service.impl.CommandLineService;
import by.training.epam.lab9.service.impl.SubjectService;
import by.training.epam.lab9.service.impl.TestService;
import by.training.epam.lab9.service.impl.UserService;

/**
 * Created by alexh on 26.10.2016.
 */
public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final ITestService testService = new TestService();
    private final IUserService userService = new UserService();
    private final ISubjectService subjectService = new SubjectService();
    private final ICommandLineService commandLineService = new CommandLineService();


    public static ServiceFactory getInstance(){
        return instance;
    }


    private ServiceFactory(){}


    public ITestService getTestService() {
        return testService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public ISubjectService getSubjectService() {
        return subjectService;
    }

    public ICommandLineService getCommandLineService() {
        return commandLineService;
    }
}
