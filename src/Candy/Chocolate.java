package Candy;

import java.util.InputMismatchException;

public class Cake extends Candy {

    String name;
    int weight;
    public double chooseCake() {
        int operation = 0;
        int amount = 0;
        double allWeihgt = 0;
        System.out.println("Пирожное:\n 1)Клубничное\n 2)С заварным кремом\n 3)Шоколадное\n  4)С ванильным кремом\n 5)Выход");

        while (true) {
            try {
                operation = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Повторите ввод");
                sc.next();
            }

            switch (operation) {
                case 1 -> {
                    name = "Клубничное";
                    weight = 17;
                }
                case 2 -> {
                    name = "С заварным кремом";
                    weight = 10;
                }
                case 3 -> {
                    name = "Шоколадное";
                    weight = 32;
                }
                case 4 -> {
                    name = "С ванильным кремом";
                    weight = 15;
                }
                case 5 ->{
                    return allWeihgt;
                }
            }
            System.out.println("Укажите количество: ");
            amount =sc.nextInt();

            allWeihgt+=(amount*weight);
        }
        return allWeihgt;
    }
}
