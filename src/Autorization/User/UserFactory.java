package Autorization.User;

public class UserFactory {
    public User createUser(String name, String login, String password, boolean ban) {
        if(name.equals("customer"))
            return new Customer(login,password, ban);
        else if(name.equals("administrator"))
            return new Administrator(login,password, ban);
        else return null;
    }
}
