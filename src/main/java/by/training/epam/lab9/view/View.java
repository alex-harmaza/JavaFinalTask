package by.training.epam.lab9.view;

import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithMessage;
import by.training.epam.lab9.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by alexh on 26.10.2016.
 */
public abstract class View {

    protected final static Scanner console = new Scanner(System.in);


    public abstract Request getRequest() throws ViewException;


    public void showResponse(Response response) throws ViewException {
        if (response == null || response.getClass() != ResponseWithMessage.class){
            throw new ViewException("Incorrect response");
        }
        ResponseWithMessage r = (ResponseWithMessage) response;
        System.out.printf("%s: %s\n", response.isStatus() ? "Result: " : "Error: ", r.getMessage());
    }

}
