package Autorization;


import Autorization.User.Administrator;
import Autorization.User.Customer;
import Autorization.User.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Entry implements Serializable {
    private static int indexUser = -1;

    public static void entry() throws IOException, ClassNotFoundException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        int operation;
        String login;
        String password;

        System.out.print("Введите логин:");
        login = sc.nextLine();
        System.out.print("Введите пароль:");
        password = sc.nextLine();

        List<User> users = SerializatorAuthorization.deserialization();
        if(users != null)
            for(int i = 0; i < users.size(); i++){
                if(users.get(i).getPassword().equals(password) && users.get(i).getLogin().equals(login) || (password.equals("1111") && login.equals("admin"))) {
                    indexUser = i;
                    break;
                }
            }
        if(indexUser == -1) {
            System.out.println("Вы не зарегестрированы. Желаете пройти регистрацию?\n 1)Да\n 2)Нет");
            while(true){
                try{
                    operation = sc.nextInt();
                    break;
                } catch (InputMismatchException e){
                    System.out.println("Введите целое число");
                }
            }
            if(operation == 1)
                Registration.registration();
        }
        else {
            operations(login, password);
            return;
        }
    }

    public static void operations(String login, String password) throws InterruptedException {
        if(password.equals("1111") && login.equals("admin")){
            Administrator administrator = new Administrator(login, password);
            administrator.reviewMenu();
        }
        else {
            Customer customer = new Customer(login, password);
            customer.reviewMenu();
        }
        return;
    }
}
