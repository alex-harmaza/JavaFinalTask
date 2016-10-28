package by.training.epam.lab9.controller;

import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithMessage;
import by.training.epam.lab9.command.ICommand;
import by.training.epam.lab9.command.exception.CommandException;
import by.training.epam.lab9.command.factory.CommandFactory;

/**
 * Created by alexh on 26.10.2016.
 */
public class Controller {

    private CommandFactory commandFactory = new CommandFactory();


    public Response doRequest(Request request){
        ICommand command = commandFactory.getCommand(request.getCommand());
        Response response;
        try {
            response = command.execute(request);
        }
        catch (CommandException e){
            response = new ResponseWithMessage(false, e.getMessage());
        }
        return response;
    }
}
