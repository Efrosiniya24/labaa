package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Biscuit extends All implements Serializable {
    static ArrayList<String> biscuitsName = new ArrayList<>();
    static ArrayList<Double> biscuitsWeight = new ArrayList<>();
    static ArrayList<String> biscuitsGift = new ArrayList<>();

    @Override
    public void addCandy() {
        biscuitsName.add(name);
        biscuitsWeight.add(weight);
    }

    @Override
    public void view() {
        System.out.println("\n___Печенье___: ");
            for (int i = 0; i < biscuitsName.size(); i++)
                   System.out.println((i + 1) + ") " + biscuitsName.get(i) + " вес: " + biscuitsWeight.get(i));
    }
    @Override
    public void addGift(int i){
        boolean t = true;
        for (int u = 0; u < biscuitsGift.size(); u++)
            if(biscuitsGift.get(u).equals(biscuitsName.get(i-1)))
                t =false;
        if(t == true)
            biscuitsGift.add(biscuitsName.get(i-1));
    }
    @Override
    public void viewGift(){
        System.out.println("\n___Печенье___: ");
        for (int i = 0; i < biscuitsGift.size(); i++)
            System.out.println((i + 1) + ") " + biscuitsGift.get(i));
    }

    @Override
    public double choose() {
        Scanner sc = new Scanner(System.in);
        int operation = 0;
        int amount = 0;
        double allWeihgt = 0;

        if (biscuitsName.size() == 0)
            System.out.println("Печенья нет");
        else {
            System.out.print("\nВыберите печенье ");
            view();
            while (true) {
                System.out.print("Номер печенья: ");
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

                allWeihgt += (amount * biscuitsWeight.get(operation - 1));

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
    public  void deserializationCandy() throws IOException, ClassNotFoundException {
        biscuitsName = Serializator.deserialization();
        biscuitsWeight = Serializator.deserialization();
    }
    @Override
    public void serializationCandy() throws IOException {
        Serializator.serialization(biscuitsName);
        Serializator.serialization(biscuitsWeight);
    }

}



