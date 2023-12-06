//Новогодний подарок. Определить иерархию конфет и прочих сладостей.
// Создать несколько объектов-конфет. Собрать детский подарок с определением его веса.

//import Autorization.Entry;
import Autorization.Entry;
import Autorization.Registration;
import Autorization.SerializatorAuthorization;
import Autorization.User.Administrator;
import Autorization.User.Customer;
import Autorization.User.User;
import Candy.*;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int operation = 0;

        while(true) {
            System.out.println("Меню:\n 1)Вход\n 2)Регистрация\n 3)Выход");
            try {
                operation = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }

            switch (operation) {
                case 1 -> Entry.entry();
                case 2 -> Registration.registration();
                case 3 -> {return ;}
            }
        }
    }
}