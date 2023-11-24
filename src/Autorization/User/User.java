package Autorization.User;

import java.io.Serializable;
import java.util.List;

public abstract class User implements Serializable {
    private String login;
    private String password;

    public User(String login, String password) {

        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    public abstract void reviewMenu() throws InterruptedException;
}
