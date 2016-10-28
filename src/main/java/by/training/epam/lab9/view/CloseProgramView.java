package by.training.epam.lab9.view;

import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.view.exception.ViewException;

/**
 * Created by alexh on 27.10.2016.
 */
public class CloseProgramView extends View {

    @Override
    public Request getRequest() throws ViewException {
        System.exit(0);
        return null;
    }

}
