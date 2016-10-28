package by.training.epam.lab9.command.impl;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.entity.Test;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithTestList;
import by.training.epam.lab9.command.ICommand;
import by.training.epam.lab9.command.exception.CommandException;
import by.training.epam.lab9.service.exception.ServiceException;
import by.training.epam.lab9.service.factory.ServiceFactory;

import java.util.List;

/**
 * Created by alexh on 26.10.2016.
 */
public class GetTests implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.SHOW_TESTS
                || request.getClass() != Request.class){
            throw new CommandException("Incorrect request");
        }

        List<Test> result;
        try {
            result = ServiceFactory.getInstance().getTestService().getTestList();
        }
        catch (ServiceException ex){
            throw new CommandException(ex.getMessage(), ex);
        }
        return new ResponseWithTestList(result);
    }

}
