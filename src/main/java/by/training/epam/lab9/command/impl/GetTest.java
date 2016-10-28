package by.training.epam.lab9.command.impl;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.dto.TestDTO;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.request.RequestWithID;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithTestDTO;
import by.training.epam.lab9.command.ICommand;
import by.training.epam.lab9.command.exception.CommandException;
import by.training.epam.lab9.service.exception.ServiceException;
import by.training.epam.lab9.service.factory.ServiceFactory;

/**
 * Created by alexh on 26.10.2016.
 */
public class GetTest implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.SHOW_TEST
                || request.getClass() != RequestWithID.class){
            throw new CommandException("Incorrect request");
        }

        TestDTO testDTO;
        try {
            testDTO = ServiceFactory.getInstance().getTestService()
                    .getTest(((RequestWithID) request).getId());
        }
        catch (ServiceException ex){
            throw new CommandException(ex.getMessage(), ex);
        }
        return new ResponseWithTestDTO(testDTO);
    }
}
