package Candy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Biscuit extends All implements Serializable {
      static List<All> biscuit = new ArrayList<>();
      static List<String> biscuitsGift = new ArrayList<>();


    @Override
    public boolean view(List<All> all) {
       int i = 1;
        for (All alls: all)
           if(alls instanceof Biscuit) {
               System.out.println((i) + ") " + alls.getName() + " вес: " + alls.getWeight());
               biscuit.add(alls);
               i++;
           }
        if( i == 1) {
            System.out.println("Печенья нет");
            return false;
        }
        else return true;
    }

    @Override
    public void addGift(int i, All all){
        if (!(biscuitsGift.contains(all.getName()))) {
            biscuitsGift.add(all.getName());
        }
    }
    @Override
    public void viewGift(){
        System.out.println("\n___Печенье___: ");
        for (int i = 0; i < biscuitsGift.size(); i++)
            System.out.println((i + 1) + ") " + biscuitsGift.get(i));
    }

    @Override
    public double choose(List<All> all) throws ArrayIndexOutOfBoundsException {
        Scanner sc = new Scanner(System.in);
        int operation;
        int amount;
        double allWeihgt = 0;
        boolean t = view(all);
        if (t) {
            while (true) {
                System.out.print("Номер печенья: ");
                while (true) {
                    try {
                        operation = sc.nextInt();
                        break;
                    } catch ( ArrayIndexOutOfBoundsException | InputMismatchException e) {
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
                addGift(operation, biscuit.get(operation - 1));
                allWeihgt += (amount * biscuit.get(operation - 1).getWeight());
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



