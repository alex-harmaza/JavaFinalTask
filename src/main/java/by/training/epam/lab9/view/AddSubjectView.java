package by.training.epam.lab9.view;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.request.RequestWithString;

/**
 * Created by alexh on 27.10.2016.
 */
public class AddSubjectView extends View {

    @Override
    public Request getRequest() {
        System.out.print("Enter the subject: ");
        return new RequestWithString(CommandEnum.ADD_SUBJECT, console.nextLine().trim());
    }

}
