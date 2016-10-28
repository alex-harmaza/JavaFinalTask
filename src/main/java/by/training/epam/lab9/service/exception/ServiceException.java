package by.training.epam.lab9.service.exception;

/**
 * Created by alexh on 25.10.2016.
 */
public class ServiceException extends Exception {

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

}
