package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Chocolate extends All implements Serializable {
    static List<All> chocolate = new ArrayList<>();
    static List<String> chocolateGift = new ArrayList<>();

    @Override
    public boolean view(List<All> all) {
        int i = 1;
        System.out.println("\n___Шоколад___: ");
        for (All alls: all)
            if(alls instanceof Chocolate) {
                System.out.println((i) + ") " + alls.getName() + " вес: " + alls.getWeight());
                chocolate.add(alls);
                i++;
            }
        if( i == 1) {
            System.out.println("Шоколада нет");
            return false;
        }
        else return true;
    }
    @Override
    public void addGift(int i, All all){
        if (!chocolateGift.contains(all.getName())) {
            chocolateGift.add(all.getName());
        }
    }
    @Override
    public void viewGift(){
        System.out.println("\n___Шоколад___: ");
        for (int i = 0; i < chocolateGift.size(); i++)
            System.out.println((i + 1) + ") " + chocolateGift.get(i));
    }

    @Override
    public double choose(List<All> all) {
        Scanner sc = new Scanner(System.in);
        int operation;
        int amount;
        double allWeihgt = 0;
        boolean t = view(all);
        if (t) {
            while (true) {
                System.out.print("Номер шоколада: ");
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

                addGift(operation, chocolate.get(operation - 1));

                allWeihgt += (amount * chocolate.get(operation - 1).getWeight());

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
