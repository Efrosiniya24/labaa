package Autorization.User;

import Candy.Candy;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends User {

    public Customer(String login, String password, boolean ban) {
        super(login, password, ban);
    }

    @Override
    public void reviewMenu() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int operation = 0;
        while(true) {
            System.out.println("""
                    Меню:
                     1)Собрать подарок
                     2)Посмотреть вес подарка             
                     3)Посмотреть новорогодний подарок
                     4)Выход""");
            try {
                operation = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
            Candy candy = new Candy();
            switch (operation) {
                case 1 -> candy.makeGift();
                case 2 -> candy.count();
                case 3 -> candy.view();
                case 4 -> {return ;}
            }
        }
    }


}
