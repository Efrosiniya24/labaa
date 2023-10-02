package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sweet extends All implements Serializable {    static ArrayList<All> marshmallow = new ArrayList<>();
    static ArrayList<All> sweet = new ArrayList<>();
    static ArrayList<String> sweetGift = new ArrayList<>();

    @Override
    public boolean view(ArrayList<All> all) {
        int i = 1;
        System.out.println("\n___Конфеты___: ");
        for (All alls: all)
            if(alls instanceof Sweet) {
                System.out.println((i) + ") " + alls.getName() + " вес: " + alls.getWeight());
                sweet.add(alls);
                i++;
            }
        if( i == 1) {
            System.out.println("Конфет нет");
            return false;
        }
        else return true;
    }
    @Override
    public void addGift(int i, All all){
        boolean t = true;
        for (int u = 0; u < sweetGift.size(); u++)
            if(sweetGift.get(u).equals(all))
                t =false;
        if(t == true)
            sweetGift.add(all.getName());
    }
    @Override
    public void viewGift(){
        System.out.println("\n___Конфеты___: ");
        for (int i = 0; i < sweetGift.size(); i++)
            System.out.println((i + 1) + ") " + sweetGift.get(i));
    }

    @Override
    public double choose(ArrayList<All> all) {
        Scanner sc = new Scanner(System.in);
        int operation;
        int amount;
        double allWeihgt = 0;
        boolean t = view(all);
        if (t) {
            while (true) {
                System.out.print("Номер конфет: ");
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

                addGift(operation, sweet.get(operation - 1));

                allWeihgt += (amount * sweet.get(operation - 1).getWeight());

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
}
