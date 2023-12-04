package Autorization.User;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Candy.*;


public class Administrator extends User {

    public Administrator(String login, String password) {
        super(login, password);
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
                    3)Изменить список
                    4)Выход из функционала администратора
                    5) Выход из системы""");
            try {
                operation = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
            Menu menu = new Menu();
            switch (operation) {
                case 1 -> menu.addMenu();
                case 2 -> { menu.deleteMenu();}
                case 5 -> {
                    return;
                }

            }
            List<All> all2 = new ArrayList<>(Serializator.deserialization());
            System.out.println(all2);
        }
    }
}
