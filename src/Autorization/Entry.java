package Autorization;


import Autorization.User.Administrator;
import Autorization.User.Customer;
import Autorization.User.User;
import Autorization.User.UserFactory;
import Candy.All;

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
        if (users != null) {
            IteratorUser<User> iterator = new IteratorUser<>(users, 0);
            for (int i = 0; iterator.hasNext(); i++) {
                User user = iterator.next();
                if (user.getPassword().equals(password) && user.getLogin().equals(login)) {
                    indexUser = i;
                    break;
                }
            }
        }
        if (indexUser == -1) {
            System.out.println("Вы не зарегестрированы. Желаете пройти регистрацию?\n 1)Да\n 2)Нет");
            while (true) {
                try {
                    operation = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Введите целое число");
                }
            }
            if (operation == 1) {
                Registration.registration();
                SerializatorAuthorization.serialization(users);
            }
        } else {
            if (!users.get(indexUser).getBan()) {
                User user = operations(login, password, users.get(indexUser).getPresent());
                users.set(indexUser, user);
                indexUser = -1;
            } else System.out.println("К сожалению, мы не можем предоставить Вам доступ. Вы заблокированы");
        }
        SerializatorAuthorization.serialization(users);
    }

    public static User operations(String login, String password, List<All> present) throws InterruptedException, IOException, ClassNotFoundException {
        UserFactory userFactory = new UserFactory();
        User user;
        if (password.equals("1111") && login.equals("admin"))
            user = userFactory.createUser("administrator", login, password, false, present);
        else
            user = userFactory.createUser("customer", login, password, false, present);
        user.reviewMenu(user);
        return user;
    }

    public static void changeUser() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int operation;
        String login;
        String password;
        String password1;
        List<User> users = SerializatorAuthorization.deserialization();
        if (users != null) {
            viewUsers(users);
            System.out.print("Введите номер пользователя: ");
            operation = printNumber(users);
            System.out.print("Введите логин:");
            login = sc.nextLine();

            while (true) {
                System.out.println("Введите новый логин и пароль\n");
                System.out.print("Введите пароль:");
                password = sc.nextLine();
                System.out.print("Введите повторный пароль:");
                password1 = sc.nextLine();
                if (!password1.equals(password))
                    System.out.println("Повторный пароль был введен неверно. Повторите операции заново");
                else break;
            }

            UserFactory userFactory = new UserFactory();
            User user;
            if (users.get(operation - 1) instanceof Customer)
                user = userFactory.createUser("customer", login, password, users.get(operation - 1).getBan(), users.get(operation - 1).getPresent());
            else
                user = userFactory.createUser("administrator", login, password, users.get(operation - 1).getBan(), null);
            users.set(operation - 1, user);
            SerializatorAuthorization.serialization(users);
        }
    }

    public static void deleteUser() throws IOException, ClassNotFoundException {
        int operation;
        List<User> users = SerializatorAuthorization.deserialization();
        if (users != null) {
            viewUsers(users);
            System.out.print("Введите номер пользователя: ");
            operation = printNumber(users);

            users.remove(operation - 1);
            SerializatorAuthorization.serialization(users);
        }
    }

    public static void banUser() throws IOException, ClassNotFoundException {
        int operation;
        List<User> users = SerializatorAuthorization.deserialization();
        if (users != null) {
            viewUsers(users);
            System.out.print("Введите номер пользователя для смены блокировки: ");
            operation = printNumber(users);

            UserFactory userFactory = new UserFactory();
            User user;
            if (users.get(operation - 1) instanceof Customer)
                user = userFactory.createUser("customer", users.get(operation - 1).getLogin(), users.get(operation - 1).getPassword(), !users.get(operation - 1).getBan(), users.get(operation - 1).getPresent());
            else
                user = userFactory.createUser("administrator", users.get(operation - 1).getLogin(), users.get(operation - 1).getPassword(), !users.get(operation - 1).getBan(), null);
            users.set(operation - 1, user);
            SerializatorAuthorization.serialization(users);
        }
    }

    private static void viewUsers(List<User> users) {
        IteratorUser<User> iterator = new IteratorUser<>(users, 0);
        System.out.println("№  Логин          Пароль       Бан");
        for (int i = 0; iterator.hasNext(); i++) {
            User user = iterator.next();
            System.out.println((i + 1) + ") " + user.getLogin() + "          " + user.getPassword() + "       " + user.getBan());
        }
    }

    private static int printNumber(List<User> users) {
        Scanner sc = new Scanner(System.in);
        int operation;
        while (true) {
            try {
                operation = sc.nextInt();
                if (operation > users.size())
                    System.out.println("Неверный номер");
                else return operation;
            } catch (InputMismatchException e) {
                System.out.println("Введите целове число");
            }
        }
    }
}
