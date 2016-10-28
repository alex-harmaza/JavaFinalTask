package by.training.epam.lab9.service.impl;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.service.ICommandLineService;
import by.training.epam.lab9.service.exception.ServiceException;

/**
 * Created by alexh on 27.10.2016.
 */
public class CommandLineService implements ICommandLineService {

    @Override
    public String showHelp() throws ServiceException {
        StringBuilder builder = new StringBuilder();
        for (CommandEnum command : CommandEnum.values()){
            builder.append(command.getDescription()).append("\n");
        }
        return builder.toString();
    }

}
