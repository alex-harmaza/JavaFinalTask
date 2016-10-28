package by.training.epam.lab9.view.exception;

/**
 * Created by alexh on 27.10.2016.
 */
public class ViewException extends Exception {

    public ViewException(String message){
        super(message);
    }

    public ViewException(String message, Throwable cause){
        super(message, cause);
    }

}
