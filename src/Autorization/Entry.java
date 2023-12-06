package Autorization;


import Autorization.User.Administrator;
import Autorization.User.Customer;
import Autorization.User.User;
import Autorization.User.UserFactory;

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
                if (users.get(i).getPassword().equals(password) && users.get(i).getLogin().equals(login)) {
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
            if (operation == 1)
                Registration.registration();
        } else {
            if (!users.get(indexUser).getBan()) {
                indexUser = -1;
                operations(login, password);

            } else System.out.println("К сожалению, мы не можем предоставить Вам доступ. Вы заблокированы");
        }
    }

    public static void operations(String login, String password) throws InterruptedException, IOException, ClassNotFoundException {
        UserFactory userFactory = new UserFactory();
        User user;
        if (password.equals("1111") && login.equals("admin")) {
            user = userFactory.createUser("administrator", login, password, false);
            user.reviewMenu();
        } else {
            user = userFactory.createUser("customer", login, password, false);
            user.reviewMenu();
        }
    }

    public static void changeUser() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int operation;
        String login;
        String password;
        List<User> users = SerializatorAuthorization.deserialization();
        if (users != null) {
            System.out.println("№  Логин          Пароль       Бан");
            IteratorUser<User> iterator = new IteratorUser<>(users, 0);
            for (int i = 0; iterator.hasNext(); i++) {
                System.out.println((i + 1) + ") " + users.get(i).getLogin() + "          " + users.get(i).getPassword() + "       " + users.get(i).getBan());
            }
            System.out.print("Введите номер пользователя: ");

            while (true) {
                try {
                    operation = sc.nextInt();
                    if (operation > users.size())
                        System.out.println("Неверный номер");
                    else break;
                } catch (InputMismatchException e) {
                    System.out.println("Введите целове число");
                }
            }

            System.out.print("Введите новый логие и пароль.\nЛогин: ");
            login = sc.next();
            System.out.print("Пароль:");
            password = sc.next();

            UserFactory userFactory = new UserFactory();
            User user;
            if (users.get(operation - 1) instanceof Customer)
                user = userFactory.createUser("customer",login, password, users.get(operation - 1).getBan());
            else user = userFactory.createUser("administrator",login, password, users.get(operation - 1).getBan());
            users.set(operation - 1, user);
            SerializatorAuthorization.serialization(users);
            System.out.println(user);
        }
    }

    public static void deleteUser() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int operation;
        List<User> users = SerializatorAuthorization.deserialization();
        if (users != null) {
            System.out.println("№  Логин          Пароль       Бан");
            IteratorUser<User> iterator = new IteratorUser<>(users, 0);
            for (int i = 0; iterator.hasNext(); i++) {
                System.out.println((i + 1) + ") " + users.get(i).getLogin() + "          " + users.get(i).getPassword() + "       " + users.get(i).getBan());
            }
            System.out.print("Введите номер пользователя: ");

            while (true) {
                try {
                    operation = sc.nextInt();
                    if (operation > users.size())
                        System.out.println("Неверный номер");
                    else break;
                } catch (InputMismatchException e) {
                    System.out.println("Введите целове число");
                }
            }
            users.remove(operation - 1);
            SerializatorAuthorization.serialization(users);
        }
    }

    public static void banUser() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int operation;
        List<User> users = SerializatorAuthorization.deserialization();
        if (users != null) {
            System.out.println("№  Логин          Пароль       Бан");
            IteratorUser<User> iterator = new IteratorUser<>(users, 0);
            for (int i = 0; iterator.hasNext(); i++) {
                System.out.println((i + 1) + ") " + users.get(i).getLogin() + "          " + users.get(i).getPassword() + "       " + users.get(i).getBan());
            }
            System.out.print("Введите номер пользователя для смены блокировки: ");

            while (true) {
                try {
                    operation = sc.nextInt();
                    if (operation > users.size())
                        System.out.println("Неверный номер");
                    else break;
                } catch (InputMismatchException e) {
                    System.out.println("Введите целове число");
                }
            }
            UserFactory userFactory = new UserFactory();
            User user;
            if (users.get(operation - 1) instanceof Customer)
                user = userFactory.createUser("customer", users.get(operation - 1).getLogin(), users.get(operation - 1).getPassword(), !users.get(operation - 1).getBan());
            else
                user = userFactory.createUser("administrator", users.get(operation - 1).getLogin(), users.get(operation - 1).getPassword(), !users.get(operation - 1).getBan());
            users.set(operation - 1, user);
            SerializatorAuthorization.serialization(users);
        }
    }
}
