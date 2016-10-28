package by.training.epam.lab9.command.impl;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.request.RequestWithToken;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithMessage;
import by.training.epam.lab9.command.ICommand;
import by.training.epam.lab9.command.exception.CommandException;
import by.training.epam.lab9.service.exception.ServiceException;
import by.training.epam.lab9.service.factory.ServiceFactory;

/**
 * Created by alexh on 26.10.2016.
 */
public class Login implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.LOGIN
                || request.getClass() != RequestWithToken.class){
            throw new CommandException("Incorrect request");
        }

        RequestWithToken requestWithToken = (RequestWithToken) request;
        boolean result;

        try {
            result = ServiceFactory.getInstance().getUserService()
                    .login(requestWithToken.getLogin(), requestWithToken.getPassword());
        }
        catch (ServiceException ex){
            throw new CommandException(ex.getMessage(), ex);
        }

        return new ResponseWithMessage(result ? "You are logged" : "You are not logged");
    }

}
