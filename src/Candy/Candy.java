package Candy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Candy {
    static double[] weightt = new double[4];
    static List<All> all2 = new ArrayList<>();
    static double minWeight;
    static double maxWeight;
    static String name = "";
    static boolean b = true;
    static int useWeight;
    static boolean useW;
    static int useName;
    static boolean useN;

    public void makeGift() {
        Scanner sc = new Scanner(System.in);

        All biscuit = new Biscuit();
        All chocolate = new Chocolate();
        All sweet = new Sweet();
        All marshmallow = new Marshmallow();

        List<All> all = new ArrayList<>();
        Filter filter = null;

        int operation;

        System.out.println("Использовать старое меню?\n 1)Да\n 2)Нет");
        operation = inputOperation();
        if (operation == 1) {
            try {
                all = Serializator.deserialization();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Ошибка ввода-вывода\n");
            }
        }
        all2.clear();
        all2.addAll(all);

        System.out.println("1)Использовать фильтры\n 2)Сбросить фильтры\n 3)Продолжить со старой фильтрацией");
        int u = inputOperation();
        if(u ==1 || u ==2) {
            System.out.println("1)Вес одной сладости\n 2)Вкус сладостей");
            operation = inputOperation();

            if (u == 2)
                if (operation == 1) {
                    useW = true;
                    useWeight = 0;
                } else {
                    useN = true;
                    useName = 0;
                }
            else if (operation == 1) {
                useW = true;
                useWeight = 1;
            } else {
                useN = true;
                useName = 1;
            }
            filter = useDeleteFilter(filter, useWeight, useName, all);
        }
        while (true) {
            System.out.println("Выберите тип сладостей:\n 1) Печенье\n 2) Шоколад\n 3) Зефир\n 4) Конфеты\n 5) Выход");
            try {
                operation = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
            switch (operation) {
                case 1 -> weightt[0] += biscuit.choose(all2);
                case 2 -> weightt[1] += chocolate.choose(all2);
                case 3 -> weightt[2] += marshmallow.choose(all2);
                case 4 -> weightt[3] += sweet.choose(all2);
                case 5 -> {
                    return;
                }
            }
        }
    }

    public void count() {
        Scanner sc = new Scanner(System.in);
        Calculate result = ((n) -> {
            double w1 = 0;
            for (int i = 0; i < 4; i++)
                w1 += n[i];
            return w1;
        });

        if (result.func(weightt) == 0) {
            System.out.println("Вы еще не добавили сладости в подарок.Желаете добавить?\n 1)Да\n 2)Нет\n");
            while (true) {
                int operation = 0;
                try {
                    operation = sc.nextInt();
                    if (operation == 1) {
                        makeGift();
                        return;
                    } else if (operation == 2)
                        break;
                } catch (InputMismatchException e) {
                    System.out.print("Повторите ввод: ");
                }
            }
        } else
            System.out.println("Общий вес подарка: " + result.func(weightt));
    }

    public void view() {
        boolean t = true;
        for (int i = 0; i < 4; i++) {
            if (weightt[i] != 0) {
                t = false;
                switch (i) {
                    case 0 -> {
                        All biscuit = new Biscuit();
                        biscuit.viewGift();
                    }
                    case 1 -> {
                        All cake = new Chocolate();
                        cake.viewGift();
                    }
                    case 2 -> {
                        All marshmallow = new Marshmallow();
                        marshmallow.viewGift();
                    }
                    case 3 -> {
                        All sweet = new Sweet();
                        sweet.viewGift();
                    }
                }
                System.out.println("Общий вес: " + weightt[i] + "\n");
            }

        }
        if (t) System.out.println("Вы не собрали подарок");
    }

    public static int inputOperation() {
        Scanner sc = new Scanner(System.in);
        int operation;
        while (true) {
            try {
                operation = sc.nextInt();
                return operation;
            } catch (InputMismatchException e) {
                System.out.print("Введите число: ");
                sc.next();
            }
        }
    }

    public static Filter useDeleteFilter(Filter filter, int useWeight, int useName, List<All> all) {
        Scanner sc = new Scanner(System.in);

        all2.clear();
        all2.addAll(all);

        if (useWeight == 1) {
            if(useW)
                while (true) {
                    System.out.println("Введите минимальный вес одной штуки");
                    try {
                        minWeight = sc.nextDouble();
                        System.out.println("Введите максимальный вес одной штуки");
                        maxWeight = sc.nextDouble();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.print("Введите число: ");
                        sc.next();
                    }
                }
            filter = new Filter(maxWeight, minWeight);
            all2 = filter.filterByWeight();
            useW = false;
        }

        if (useName == 1) {
            if(useN) {
                System.out.println("Введите вкус:");
                name = sc.next();
            }
            filter = new Filter(name);
            all2 = filter.filterByWord();
            useN = false;
        }
        return filter;
    }
}


