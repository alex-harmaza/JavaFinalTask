package by.training.epam.lab9.command.impl;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.request.RequestWithString;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithMessage;
import by.training.epam.lab9.command.ICommand;
import by.training.epam.lab9.command.exception.CommandException;
import by.training.epam.lab9.service.exception.ServiceException;
import by.training.epam.lab9.service.factory.ServiceFactory;

/**
 * Created by alexh on 27.10.2016.
 */
public class AddSubject implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.ADD_SUBJECT
                || request.getClass() != RequestWithString.class){
            throw new CommandException("Incorrect request");
        }

        try {
            ServiceFactory.getInstance().getSubjectService()
                    .addSubject(((RequestWithString) request).getValue());
        }
        catch (ServiceException ex){
            throw new CommandException(ex.getMessage(), ex);
        }
        return new ResponseWithMessage("Subject added");
    }
}
