package Candy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Marshmallow extends All implements Serializable {
    static List<All> marshmallow = new ArrayList<>();
    static List<String> marshmallowGift = new ArrayList<>();

    @Override
    public boolean view(List<All> all) {
        int i = 1;
        for (All alls : all)
            if (alls instanceof Marshmallow) {
                System.out.println((i) + ") " + alls.getName() + " вес: " + alls.getWeight());
                marshmallow.add(alls);
                i++;
            }
        if (i == 1) {
            System.out.println("Зефира нет");
            return false;
        } else return true;
    }

    @Override
    public void addGift(int i, All all) {
        if (!marshmallowGift.contains(all.getName())) {
            marshmallowGift.add(all.getName());
        }
    }

    @Override
    public void changeCandyMenu(List<All> all, All marshmallow, int num){
        all.set(num, marshmallow);
    }

    @Override
    public void viewGift() {
        System.out.println("\n___Зефир___: ");
        for (int i = 0; i < marshmallowGift.size(); i++)
            System.out.println((i + 1) + ") " + marshmallowGift.get(i));
    }

    @Override
    public void delete(List<All> all,int i){  all.remove(i);
    }

    @Override
    public int chooseNumber() {
        if(!marshmallow.isEmpty()) {
            Scanner sc = new Scanner(System.in);
            int number = 0;
            while (true) {
                System.out.print("Введите номер печенья: ");
                try {
                    number = sc.nextInt();
                    if (number >= marshmallow.size())
                        System.out.println("Такого зефира нет( Повторите ввод...");
                    else break;
                } catch (InputMismatchException e) {
                    System.out.print("Ошибка ввода( Повторите ввод: ");
                }
                sc.nextLine();
            }
            return number - 1;
        }
        return 0;
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
