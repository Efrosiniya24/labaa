package Autorization.User;


import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import Candy.*;


public class Administrator extends User {

    public Administrator(String login, String password) {
        super(login, password);
    }

    @Override
    public void reviewMenu() {
        Scanner sc = new Scanner(System.in);
        int operation = 0;
        while (true) {
            System.out.println("""
                    Меню:
                    1)Добавить сладости в меню
                    2)Выход из функционала администратора
                    3) Выход из системы""");
            try {
                operation = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
            Menu menu = new Menu();
            switch (operation) {
                case 1 -> menu.makeMenu();
                case 2 -> {
                    return;
                }

            }
        }
    }
}
