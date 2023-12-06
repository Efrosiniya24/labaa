package Autorization;

import Autorization.User.Customer;
import Autorization.User.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Registration implements Serializable {
    static List<User> users = new ArrayList<>();
    public static void registration() throws IOException, ClassNotFoundException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        int operation = 0;
        while (true) {
            System.out.print("Введите логин:");
            String login = sc.nextLine();
            String password;
            while (true) {
                String password1;
                System.out.print("Введите пароль:");
                password = sc.nextLine();
                System.out.print("Введите повторный пароль:");
                password1 = sc.nextLine();
                if (!password1.equals(password))
                    System.out.println("Повторный пароль был введен неверно. Повторите операции заново");
                else break;
            }

            users = SerializatorAuthorization.deserialization();
            for (User user : users) {
                if (user.getPassword().equals(password) && user.getLogin().equals(login) || password.equals("1111") && login.equals("admin")) {
                    System.out.println("Такой пользовтель уже существует. Вы желаете войти в систему или зарегистрироваться?/n 1)Войти/n 2)Зарегистрироваться заново");
                    while (true) {
                        try {
                            operation = sc.nextInt();
                            if (operation == 1 || operation == 2)
                                break;
                            else System.out.println("Введите 1 или 2");
                        } catch (InputMismatchException e) {
                            System.out.println("Введите целое число");
                        }
                    }
                    if (operation == 1) {
                        Entry.operations(login, password);
                    } else break;
                }
            }
            if (operation != 2) {
                User customer = new Customer(login, password, false);
                users.add(customer);
                SerializatorAuthorization.serialization(users);
                System.out.println("Регистрация проведена успешно. Вы вошли в систему в качестве пользователя.");
                Entry.operations(login, password);
                return;
            }
        }
    }
}
