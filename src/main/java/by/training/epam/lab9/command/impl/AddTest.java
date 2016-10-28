package by.training.epam.lab9.command.impl;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.request.RequestWithTestDTO;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithMessage;
import by.training.epam.lab9.command.ICommand;
import by.training.epam.lab9.command.exception.CommandException;
import by.training.epam.lab9.service.exception.ServiceException;
import by.training.epam.lab9.service.factory.ServiceFactory;

/**
 * Created by alexh on 26.10.2016.
 */
public class AddTest implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.ADD_TEST
                || request.getClass() != RequestWithTestDTO.class){
            throw new CommandException("Incorrect request");
        }

        try {
            ServiceFactory.getInstance().getTestService()
                    .addTest(((RequestWithTestDTO) request).getTestDTO());
        }
        catch (ServiceException e){
            throw new CommandException(e.getMessage(), e);
        }
        return new ResponseWithMessage("Test added");
    }

}
