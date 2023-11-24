package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static Candy.Candy.all2;

public class Menu implements Serializable {
    public void makeMenu() {

        List<All> all = new ArrayList<>();

        System.out.println("Желаете создать новое меню?\n 1)Да\n 2)Нет");
        int operation = inputOperation();

        if (operation != 1) {
            try {
                all.addAll(Serializator.deserialization());
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Ошибка ввода-вывода\n");
            }
        }

        while (true) {
            System.out.println("""
                    Выберите группу сладостей:
                    1)Печенье
                    2)Шоколад
                    3)Конфеты
                    4)Зефир
                    5)Выход""");
            operation = inputOperation();

            switch (operation) {
                case 1 -> {
                    All biscuit = new Biscuit();
                    biscuit.setName(biscuit.addName());
                    biscuit.setWeight(biscuit.addWeight());
                    all.add(biscuit);
                }
                case 2 -> {
                    All chocolate = new Chocolate();
                    chocolate.setName(chocolate.addName());
                    chocolate.setWeight(chocolate.addWeight());
                    all.add(chocolate);
                }
                case 3 -> {
                    All sweet = new Sweet();
                    sweet.setName(sweet.addName());
                    sweet.setWeight(sweet.addWeight());
                    all.add(sweet);
                }
                case 4 -> {
                    All marshmallow = new Marshmallow();
                    marshmallow.setName(marshmallow.addName());
                    marshmallow.setWeight(marshmallow.addWeight());
                    all.add(marshmallow);
                }
                case 5 -> {
                    saveFile(all);
                    return;
                }
            }
        }

    }

    public static void saveFile(List<All> all2) {
        int operation;

        while (true) {
            System.out.println("Сохранить меню?\n 1)Да\n 2)Нет");
            operation = inputOperation();
            if (operation == 1)
                try {
                    Serializator.serialization(all2);
                    System.out.println("Данные записаны в файл");
                    return;
                } catch (IOException e) {
                    System.err.println("Ошибка ввода-вывода\n");
                }
            else if (operation == 2)
                return;
        }
    }

    public static int inputOperation() {
        Scanner sc = new Scanner(System.in);
        int operation;
        while (true) {
            try {
                operation = sc.nextInt();
                return operation;
            } catch (InputMismatchException e) {
                System.out.print("Введите число: ");
                sc.next();
            }
        }
    }
}
