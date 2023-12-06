package Autorization.User;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Autorization.Entry;
import Candy.*;


public class Administrator extends User {

    public Administrator(String login, String password, boolean ban) {
        super(login, password, ban);
    }

    @Override
    public void reviewMenu() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int operation = 0;
        while (true) {
            System.out.println("""
                    Меню:
                    1)Добавить сладости в меню
                    2)Удалить сладости из меню
                    3)Изменить данные в меню
                    4)Изменить данные о пользователе
                    5)Удалить пользователя
                    6)Блоктровать пользователей
                    7)Выход из функционала администратора
                    8) Выход из системы""");
            try {
                operation = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
            Menu menu = new Menu();
            switch (operation) {
                case 1 -> menu.addMenu();
                case 2 -> menu.deleteMenu();
                case 3 -> menu. changeMenu();
                case 4 -> Entry.changeUser();
                case 5 -> Entry.deleteUser();
                case 6 -> Entry.banUser();
                case 7 -> {
                    return;
                }

            }
            List<All> all2 = new ArrayList<>(Serializator.deserialization());
            System.out.println(all2);
        }
    }
}
