package by.training.epam.lab9.command.exception;

/**
 * Created by alexh on 26.10.2016.
 */
public class CommandException extends Exception{

    public CommandException(String message){
        super(message);
    }

    public CommandException(String message, Throwable cause){
        super(message, cause);
    }
}
