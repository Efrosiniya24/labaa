package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class Chocolate extends All implements Serializable {
    static ArrayList<String> chocolateName = new ArrayList<>();
    static ArrayList<Double> chocolateWeight = new ArrayList<>();
    static ArrayList<String> chocolateGift = new ArrayList<>();

    @Override
    public void addCandy()  {
        chocolateName.add(name);
        chocolateWeight.add(weight);
    }
    @Override
    public void addGift(int i){
        boolean t = true;
        for (int u = 0; u < chocolateGift.size(); u++)
            if(chocolateGift.get(u) == chocolateName.get(i-1))
                t =false;
        if(t == true)
            chocolateGift.add(chocolateName.get(i-1));
    }
    public void viewGift(){
        System.out.println("\n___Шоколад____: ");
        for (int i = 0; i < chocolateName.size(); i++)
            System.out.println((i + 1) + ") " + chocolateName.get(i));
    }

    @Override
    public void view() {
        System.out.println("___Шоколад____");
            for (int i = 0; i < chocolateWeight.size(); i++)
               System.out.println((i + 1) + ") " + chocolateName.get(i) + " вес: " + chocolateWeight.get(i));
    }

    @Override
    public double choose() {
        int operation = 0;
        int amount = 0;
        double allWeihgt = 0;

        if(chocolateName.size() == 0 )
            System.out.println("Шоколада нет");
        else {
            while (true) {
                System.out.print("Выберите шоколад");
                view();
                while (true) {
                    System.out.print("Номер шоколада: ");
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
                allWeihgt += (amount * chocolateWeight.get(operation - 1));
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
        Serializator.serialization(chocolateName);
        Serializator.serialization(chocolateWeight);
    }
    @Override
    public  void deserializationCandy() throws IOException, ClassNotFoundException {
        chocolateName = Serializator.deserialization();
        chocolateWeight = Serializator.deserialization();
    }
}
