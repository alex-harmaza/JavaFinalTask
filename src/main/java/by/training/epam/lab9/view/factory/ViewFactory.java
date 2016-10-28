package by.training.epam.lab9.view.factory;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.view.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexh on 26.10.2016.
 */
public class ViewFactory {

    private Map<CommandEnum, View> map;


    public ViewFactory(){
        map = new HashMap<>();
        map.put(CommandEnum.LOGIN, new LoginView());
        map.put(CommandEnum.REGISTER, new RegisterView());
        map.put(CommandEnum.ADD_TEST, new AddTestView());
        map.put(CommandEnum.SHOW_TEST, new GetTestView());
        map.put(CommandEnum.SHOW_TESTS, new GetTestsView());
        map.put(CommandEnum.ADD_SUBJECT, new AddSubjectView());
        map.put(CommandEnum.SHOW_SUBJECTS, new GetSubjectsView());
        map.put(CommandEnum.HELP, new ShowHelpView());
        map.put(CommandEnum.CLOSE, new CloseProgramView());
    }


    public View getView(CommandEnum command){
        return map.get(command);
    }

}
