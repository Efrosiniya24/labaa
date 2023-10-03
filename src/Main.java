//Новогодний подарок. Определить иерархию конфет и прочих сладостей.
// Создать несколько объектов-конфет. Собрать детский подарок с определением его веса.

import Candy.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int operation = 0;
        Candy candy  = new Candy();
        Menu menu = new Menu();
        while(true) {
            System.out.println("Меню:\n 1)Собрать подарок\n" +
                    " 2)Посмотреть вес подарка\n" +
                    " 3)Добавить сладости в меню\n " +
                    " 4)Посмотреть новорогодний подарок\n"+
                    "5)Выход ");

                try {
                    operation = sc.nextInt();
                } catch (InputMismatchException e) {
                    sc.next();
                }

            switch (operation) {
                case 1 -> candy.makeGift();
                case 2 -> candy.count();
                case 3 -> menu.makeMenu();
                case 4 -> candy.view();
                case 5 -> {
                    return ;
                }
            }
        }
    }
}