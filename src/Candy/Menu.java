package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements Serializable {
    static All biscuit = new Biscuit();
    static All chocolate = new Chocolate();
    static All sweet = new Sweet();
    static All marshmallow = new Marshmallow();

    public void makeMenu() {
        int operation;
        System.out.println("Желаете создать новое меню?\n 1)Да\n 2)Нет");
        operation  = inputOperation();
        if(operation !=1) {
            try {
                deserializationMenu();
            }catch(IOException | ClassNotFoundException e){
                System.err.println("Ошибка ввода-вывода\n");
            }
        }
        while (true) {
            System.out.println("\nВыберите группу сладостей:\n 1)Печенье\n 2)Шоколад\n 3)Конфеты\n 4)Зефир\n 5)Выход");
            operation  = inputOperation();

            switch (operation) {
                case 1 -> {
                    biscuit.add();
                    biscuit.addCandy();
                }
                case 2 -> {
                    chocolate.add();
                    chocolate.addCandy();
                }
                case 3 -> {
                    sweet.add();
                    sweet.addCandy();
                }
                case 4 -> {
                    marshmallow.add();
                    marshmallow.addCandy();
                }
                case 5 -> {
                    saveFile();
                    return;
                }
            }
        }

    }

    public static void saveFile(){
        int operation = 0;

        while(true) {
            System.out.println("Сохранить меню?\n 1)Да\n 2)Нет");
            operation  = inputOperation();
            if(operation == 1)
                try {
                    biscuit.serializationCandy();
                    chocolate.serializationCandy();
                    marshmallow.serializationCandy();
                    sweet.serializationCandy();
                    Candy.serialization();
                    System.out.println("Данные записаны в файл");
                    return;
                }catch(IOException e){
                    System.err.println("Ошибка ввода-вывода\n");
                }
            else if(operation ==2)
                break;
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
    public static void deserializationMenu() throws IOException, ClassNotFoundException {
        biscuit.deserializationCandy();
        chocolate.deserializationCandy();
        marshmallow.deserializationCandy();
        sweet.deserializationCandy();
    }

}
