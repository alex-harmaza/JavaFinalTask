package by.training.epam.lab9.entity;

import by.training.epam.lab9.bean.entity.UserRole;

/**
 * Created by alexh on 28.10.2016.
 */
public class XmlUser {

    private String login;
    private String password;
    private UserRole role;


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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
