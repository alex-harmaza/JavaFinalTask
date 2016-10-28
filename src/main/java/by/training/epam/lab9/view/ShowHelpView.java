package by.training.epam.lab9.view;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithMessage;
import by.training.epam.lab9.view.exception.ViewException;

/**
 * Created by alexh on 27.10.2016.
 */
public class ShowHelpView extends View {

    @Override
    public Request getRequest() throws ViewException {
        return new Request(CommandEnum.HELP);
    }


    @Override
    public void showResponse(Response response) throws ViewException {
        System.out.println("Result: \n" + ((ResponseWithMessage) response).getMessage());
    }
}
