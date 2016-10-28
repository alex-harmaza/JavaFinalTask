package by.training.epam.lab9.bean.request;

import by.training.epam.lab9.CommandEnum;

/**
 * Created by alexh on 26.10.2016.
 */
public class RequestWithToken extends Request {

    private String login;
    private String password;


    public RequestWithToken(CommandEnum command, String login, String password){
        super(command);
        setLogin(login);
        setPassword(password);
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
