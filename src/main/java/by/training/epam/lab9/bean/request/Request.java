package by.training.epam.lab9.bean.request;

import by.training.epam.lab9.CommandEnum;

/**
 * Created by alexh on 26.10.2016.
 */
public class Request {

    private CommandEnum command;


    public Request(CommandEnum command){
        setCommand(command);
    }


    public CommandEnum getCommand() {
        return command;
    }

    public void setCommand(CommandEnum command) {
        this.command = command;
    }
}
