package by.training.epam.lab9.command.factory;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.command.ICommand;
import by.training.epam.lab9.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexh on 26.10.2016.
 */
public class CommandFactory {

    private Map<CommandEnum, ICommand> map;


    public CommandFactory(){
        map = new HashMap<>();
        map.put(CommandEnum.ADD_TEST, new AddTest());
        map.put(CommandEnum.LOGIN, new Login());
        map.put(CommandEnum.REGISTER, new Register());
        map.put(CommandEnum.SHOW_TEST, new GetTest());
        map.put(CommandEnum.SHOW_TESTS, new GetTests());
        map.put(CommandEnum.ADD_SUBJECT, new AddSubject());
        map.put(CommandEnum.SHOW_SUBJECTS, new GetSubjects());
        map.put(CommandEnum.HELP, new ShowHelp());
    }


    public ICommand getCommand(CommandEnum command){
        return map.get(command);
    }

}
