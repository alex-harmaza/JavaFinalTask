package by.training.epam.lab9.bean.request;

import by.training.epam.lab9.CommandEnum;

/**
 * Created by alexh on 27.10.2016.
 */
public class RequestWithString extends Request {

    private String value;


    public RequestWithString(CommandEnum command, String value) {
        super(command);
        setValue(value);
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
