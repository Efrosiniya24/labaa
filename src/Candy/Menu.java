package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Menu implements Serializable {
    public void addMenu() throws IOException, ClassNotFoundException {

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
                    Facade facade = new Facade();
                    all.add(facade.addBiscuitMenu());
                }
                case 2 -> {
                    Facade facade = new Facade();
                    all.add(facade.addChocolateMenu());
                }
                case 3 -> {
                    Facade facade = new Facade();
                    all.add(facade.addSweetMenu());
                }
                case 4 -> {
                    Facade facade = new Facade();
                    all.add(facade.addMarshmallowMenu());
                }
                case 5 -> {
                    saveFile(all);
                    return;
                }
            }
        }

    }

    public void deleteMenu() throws IOException, ClassNotFoundException {
        List<All> all = new ArrayList<>();
        try {
            all.addAll(Serializator.deserialization());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка ввода-вывода\n");
        }
        while (true) {
            System.out.println("""
                    Выберите группу сладостей:
                    1)Печенье
                    2)Шоколад
                    3)Конфеты
                    4)Зефир
                    5)Выход""");
            int operation = inputOperation();

            switch (operation) {
                case 1 -> {
                    All biscuit = new Biscuit();
                    biscuit.view(all);
                    int num = biscuit.chooseNumber();
                    if (num != -1)
                        biscuit.delete(all, num);
                    else
                        System.out.println("Печенья нет...");
                }
                case 2 -> {
                    All chocolate = new Chocolate();
                    chocolate.view(all);
                    int num = chocolate.chooseNumber();
                    if(num != 0)
                        chocolate.delete(all,num);
                    else
                        System.out.println("Шоколада нет...");
                }
                case 3 -> {
                    All sweet = new Sweet();
                    sweet.view(all);
                    int num = sweet.chooseNumber();
                    if(num != 0)
                        sweet.delete(all,num);
                    else
                        System.out.println("Конфет нет...");
                }
                case 4 -> {
                    All marshmallow = new Marshmallow();
                    marshmallow.view(all);
                    int num =marshmallow.chooseNumber();
                    if(num != 0)
                        marshmallow.delete(all,num);
                    else
                        System.out.println("Зефира нет...");
                }
                case 5 -> {
                    saveFile(all);
                    return;
                }
            }
        }
    }

    public void changeMenu() throws IOException, ClassNotFoundException {
        List<All> all = new ArrayList<>();
        try {
            all.addAll(Serializator.deserialization());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка ввода-вывода\n");
        }
        while (true) {
            System.out.println("""
                    Выберите группу сладостей:
                    1)Печенье
                    2)Шоколад
                    3)Конфеты
                    4)Зефир
                    5)Выход""");
            int operation = inputOperation();

            switch (operation) {
                case 1 -> {
                    All biscuit = new Biscuit();
                    biscuit.view(all);
                    int num = biscuit.chooseNumber();
                    if (num != -1) {
                        biscuit.setName(biscuit.addName());
                        biscuit.setWeight(biscuit.addWeight());
                        biscuit.changeCandyMenu(all, biscuit, num);
                    }
                    else
                        System.out.println("Печенья нет...");
                }
                case 2 -> {
                    All chocolate = new Chocolate();
                    chocolate.view(all);
                    int num = chocolate.chooseNumber();
                    if (num != -1) {
                        chocolate.setName(chocolate.addName());
                        chocolate.setWeight(chocolate.addWeight());
                        chocolate.changeCandyMenu(all, chocolate, num);
                    }
                    else
                        System.out.println("Шоколада нет...");
                }
                case 3 -> {
                    All sweet = new Sweet();
                    sweet.view(all);
                    int num = sweet.chooseNumber();
                    if (num != -1) {
                        sweet.setName(sweet.addName());
                        sweet.setWeight(sweet.addWeight());
                        sweet.changeCandyMenu(all, sweet, num);
                    }
                    else
                        System.out.println("Конфет нет...");
                }
                case 4 -> {
                    All marshmallow = new Marshmallow();
                    marshmallow.view(all);
                    int num =marshmallow.chooseNumber();
                    if (num != -1) {
                        marshmallow.setName(marshmallow.addName());
                        marshmallow.setWeight(marshmallow.addWeight());
                        marshmallow.changeCandyMenu(all, marshmallow, num);
                    }
                    else
                        System.out.println("Зефира нет...");
                }
                case 5 -> {
                    saveFile(all);
                    return;
                }
            }
        }
    }

    public static void saveFile(List<All> all) throws IOException, ClassNotFoundException {
        int operation;
        List<All> all2 = new ArrayList<>();
        System.out.println(all);
        while (true) {
            System.out.println("Сохранить меню?\n 1)Да\n 2)Нет");
            operation = inputOperation();
            if (operation == 1) {
                try {
                    Serializator.serialization(all);
                    System.out.println(all);
                    System.out.println("Данные записаны в файл");
                    return;
                } catch (IOException e) {
                    System.err.println("Ошибка ввода-вывода\n");
                }
                all2.addAll(Serializator.deserialization());
                System.out.println(all2);
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
