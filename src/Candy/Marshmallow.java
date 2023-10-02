package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Marshmallow extends All implements Serializable {    static ArrayList<All> chocolate = new ArrayList<>();
    static ArrayList<All> marshmallow = new ArrayList<>();
    static ArrayList<String> marshmallowGift = new ArrayList<>();

    @Override
    public boolean view(ArrayList<All> all) {
        int i = 1;
        System.out.println("\n___Зефир___: ");
        for (All alls: all)
            if(alls instanceof Marshmallow) {
                System.out.println((i) + ") " + alls.getName() + " вес: " + alls.getWeight());
                chocolate.add(alls);
                i++;
            }
        if( i == 1) {
            System.out.println("Зефира нет");
            return false;
        }
        else return true;
    }
    @Override
    public void addGift(int i, All all){
        boolean t = true;
        for (int u = 0; u < marshmallowGift.size(); u++)
            if(marshmallowGift.get(u).equals(all))
                t =false;
        if(t == true)
            marshmallowGift.add(all.getName());
    }
    @Override
    public void viewGift(){
        System.out.println("\n___Зефир___: ");
        for (int i = 0; i < marshmallowGift.size(); i++)
            System.out.println((i + 1) + ") " + marshmallowGift.get(i));
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

                addGift(operation, marshmallow.get(operation - 1));

                allWeihgt += (amount * marshmallow.get(operation - 1).getWeight());

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
