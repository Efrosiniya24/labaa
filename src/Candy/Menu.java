package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements Serializable {
    public void makeMenu() {
        All biscuit = new Biscuit();
        All chocolate = new Chocolate();
        All sweet = new Sweet();
        All marshmallow = new Marshmallow();

        ArrayList<All> all = new ArrayList<>();

        int operation;

        System.out.println("Желаете создать новое меню?\n 1)Да\n 2)Нет");
        operation  = inputOperation();

        if(operation !=1) {
            try {
                all = Serializator.deserialization();
            }catch(IOException | ClassNotFoundException e){
                System.err.println("Ошибка ввода-вывода\n");
            }
        }

        while (true) {
            System.out.println("\nВыберите группу сладостей:\n 1)Печенье\n 2)Шоколад\n 3)Конфеты\n 4)Зефир\n 5)Выход");
            operation  = inputOperation();

            switch (operation) {
                case 1 -> {
                    //biscuit = new Biscuit(biscuit.addName());
                    biscuit.setName(biscuit.addName());
                    biscuit.setWeight(biscuit.addWeight());
                    all.add(biscuit);
                }
                case 2 -> {
                    chocolate.setName(chocolate.addName());
                    chocolate.setWeight(chocolate.addWeight());
                    all.add(chocolate);
                }
                case 3 -> {
                    sweet.setName(sweet.addName());
                    sweet.setWeight(sweet.addWeight());
                    all.add(sweet);
                }
                case 4 -> {
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

    public static void saveFile(ArrayList<All> all){
        int operation = 0;

        while(true) {
            System.out.println("Сохранить меню?\n 1)Да\n 2)Нет");
            operation  = inputOperation();
            if(operation == 1)
                try {
                    Serializator.serialization(all);
                    System.out.println("Данные записаны в файл");
                    return;
                }catch(IOException e){
                    System.err.println("Ошибка ввода-вывода\n");
                }
            else if(operation ==2)
                return;
        }
    }
    public static int inputOperation(){
        Scanner sc = new Scanner(System.in);
        int operation = 0;
        while(true) {
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
