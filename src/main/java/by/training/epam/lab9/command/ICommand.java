package by.training.epam.lab9.command;

import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.command.exception.CommandException;

/**
 * Created by alexh on 26.10.2016.
 */
public interface ICommand {

    Response execute(Request request) throws CommandException;

}
