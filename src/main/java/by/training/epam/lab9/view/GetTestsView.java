package by.training.epam.lab9.view;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.entity.Test;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithTestList;
import by.training.epam.lab9.view.exception.ViewException;

import java.util.List;

/**
 * Created by alexh on 26.10.2016.
 */
public class GetTestsView extends View {

    @Override
    public Request getRequest() throws ViewException {
        return new Request(CommandEnum.SHOW_TESTS);
    }


    @Override
    public void showResponse(Response response) throws ViewException {
        if (response == null || response.getClass() != ResponseWithTestList.class){
            super.showResponse(response);
            return;
        }

        List<Test> testList = ((ResponseWithTestList) response).getTestList();
        testList.forEach(t -> System.out.printf("\t%d. %s [subject: %s; error threshold: %d]\n",
                t.getId(), t.getName(), t.getSubject().getName(), t.getErrorThreshold()));
    }
}
