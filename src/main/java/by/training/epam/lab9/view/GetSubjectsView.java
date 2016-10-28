package by.training.epam.lab9.view;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.entity.Subject;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithSubjectList;
import by.training.epam.lab9.view.exception.ViewException;

import java.util.List;

/**
 * Created by alexh on 27.10.2016.
 */
public class GetSubjectsView extends View {

    @Override
    public Request getRequest() throws ViewException {
        return new Request(CommandEnum.SHOW_SUBJECTS);
    }


    @Override
    public void showResponse(Response response) throws ViewException {
        if (response == null || response.getClass() != ResponseWithSubjectList.class){
            super.showResponse(response);
            return;
        }

        List<Subject> subjects = ((ResponseWithSubjectList) response).getSubjectList();
        subjects.forEach(s -> System.out.printf("%d. %s\n", s.getId(), s.getName()));
    }
}
