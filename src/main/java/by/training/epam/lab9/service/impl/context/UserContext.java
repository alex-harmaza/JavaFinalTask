package by.training.epam.lab9.service.impl.context;

import by.training.epam.lab9.bean.entity.UserRole;

/**
 * Created by alexh on 26.10.2016.
 */
public class UserContext {

    private final static UserContext instance = new UserContext();
    private UserRole role;


    public static UserContext getInstance(){
        return instance;
    }


    private UserContext(){}


    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
