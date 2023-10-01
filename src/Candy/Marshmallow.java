package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Marshmallow extends All implements Serializable {
    static ArrayList<String> marshmallowName = new ArrayList<>();
    static ArrayList<Double> marshmallowWeight = new ArrayList<>();
    static ArrayList<String> marshmallowGift = new ArrayList<>();

    @Override
    public void addCandy() {
        marshmallowName.add(name);
        marshmallowWeight.add(weight);
    }

    @Override
    public void view() {
        System.out.println("\n___Зефир___: ");
            for (int i = 0; i < marshmallowName.size(); i++)
              System.out.println((i + 1) + ") " + marshmallowName.get(i) + " вес: " + marshmallowWeight.get(i));
    }
    @Override
    public void addGift(int i){
        boolean t = true;
        for (int u = 0; u < marshmallowGift.size(); u++)
            if(marshmallowGift.get(u) == marshmallowName.get(i-1))
                t =false;
        if(t == true)
            marshmallowGift.add(marshmallowName.get(i-1));
    }

    public void viewGift(){
        System.out.println("\n___Зефир___: ");
        for (int i = 0; i < marshmallowGift.size(); i++)
            System.out.println((i + 1) + ") " + marshmallowGift.get(i));
    }

    @Override
    public double choose() {
        Scanner sc = new Scanner(System.in);
        int operation = 0;
        int amount = 0;
        double allWeihgt = 0;

        if(marshmallowWeight.size() == 0 )
            System.out.println("Зефира нет");
        else {


            while (true) {
                System.out.print("Выберите зефир:");
                view();
                System.out.print("Номер зефира: ");
                while (true) {
                    try {
                        operation = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Повторите ввод");
                        sc.next();
                    }
                }
                System.out.println("Укажите количество: ");
                while (true) {
                    try {
                        amount = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Повторите ввод");
                        sc.next();
                    }
                }
                this.addGift(operation);
                allWeihgt += (amount * marshmallowWeight.get(operation - 1));

                System.out.println("Продолжить?\n 1)Да\n 2)Нет");

                while (true) {
                    try {
                        operation = sc.nextInt();
                        if (operation == 2)
                            return allWeihgt;
                        else break;
                    } catch (InputMismatchException e) {
                        System.out.print("Повторите ввод: ");
                    }
                }
            }
        }
        return 0;
    }
    @Override
    public void serializationCandy() throws IOException {
        Serializator.serialization(marshmallowName);
        Serializator.serialization(marshmallowWeight);
    }
    @Override
    public  void deserializationCandy() throws IOException, ClassNotFoundException {
        marshmallowName = Serializator.deserialization();
        marshmallowWeight = Serializator.deserialization();
    }
}
