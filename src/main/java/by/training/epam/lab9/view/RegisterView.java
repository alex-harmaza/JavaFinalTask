package by.training.epam.lab9.view;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.request.RequestWithToken;
import by.training.epam.lab9.view.exception.ViewException;

/**
 * Created by alexh on 26.10.2016.
 */
public class RegisterView extends View {

    @Override
    public Request getRequest() throws ViewException {

        System.out.print("Enter the login: ");
        String login = console.nextLine();

        System.out.print("Enter the password: ");
        String password = console.nextLine();

        return new RequestWithToken(CommandEnum.REGISTER, login, password);
    }

}
