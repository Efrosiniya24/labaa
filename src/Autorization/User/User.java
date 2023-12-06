package Autorization.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public abstract class User implements Serializable {
    private String login;
    private String password;
    private boolean ban;

    public User(String login, String password, boolean ban) {

        this.login = login;
        this.password = password;
        this.ban = ban;
    }

//    public  void changeUser(List<User> users, int num, User user){
//        users.set(num, user);
//    }

    public String getLogin() {
        return login;
    }

    public boolean getBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public String getPassword() {
        return password;
    }
    public abstract void reviewMenu() throws InterruptedException, IOException, ClassNotFoundException;

}
