package Candy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Candy {
    private static double[] weightt = new double[4];
    protected static List<All> all2 = new ArrayList<>();
    protected static List<All> all3 = new ArrayList<>();
    private static String name;
    private static List<All> all = new ArrayList<>();
    private static final All biscuit = new Biscuit();
    private static final All chocolate = new Chocolate();
    private static final All sweet = new Sweet();
    private static final All marshmallow = new Marshmallow();
    private static double minWeight;
    private static double maxWeight;
    private static int useWeight;
    private static boolean useW;
    private static int useName;
    private static boolean useN;
    private static int sort;
    private static boolean cancelSort;

    public void makeGift() throws InterruptedException {
        int operation;

        try {
            all = Serializator.deserialization();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка ввода-вывода\n");
        }

        all3.clear();
        all3.addAll(all);

        all2.clear();
        all2.addAll(all);
        while (true) {

            System.out.println("Меню:\n 1)Фильтры\n 2)Отсортировать\n 3)Отменить сортировку\n 4)Выбрать сладости\n 5)Выход\n");
            operation = inputOperation();
            switch (operation) {
                case 1 -> useFilter();
                case 2 -> sort();
                case 3 -> cancelSorting();
                case 4 -> chooseCandy();
                case 5 -> {
                    return;
                }
            }
        }
    }

    public static void useFilter() throws InterruptedException {
        int operation;
        Filter filter = null;
        while(true) {
            System.out.println("1)Использовать фильтры\n 2)Сбросить фильтры\n");
            int u = inputOperation();
            if (u == 1 || u == 2) {
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
                filter = useDeleteFilter(filter, useWeight, useName, cancelSort);
                break;
            }
        }
    }

    public static void chooseCandy() {
        Scanner sc = new Scanner(System.in);
        int operation = 0;
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
                int operation;
                try {
                    operation = sc.nextInt();
                    if (operation == 1) {
                        makeGift();
                        return;
                    } else if (operation == 2)
                        break;
                } catch (InputMismatchException | InterruptedException e) {
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

    public static Filter useDeleteFilter(Filter filter, int useWeight, int useName, boolean cancelSort) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        all2.clear();
        if (useName == 0 && useWeight == 0 && !cancelSort) {
            all2.addAll(all);
            if (sort==1) sort1();
            else if (sort ==2) sort2();
            return filter;
        } else if ((useName == 0 || useWeight == 0) && !cancelSort) {
            all2.addAll(all);
            if (sort == 1) sort1();
            else if( sort == 2) sort2();
        }
        else if (cancelSort)
            all2.addAll(all);
        else
            all2.addAll(all3);

        if (useWeight == 1) {
            if (useW)
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
            if (useN) {
                System.out.println("Введите вкус:");
                name = sc.next();
            }
            filter = new Filter(name);
            all2 = filter.filterByWord();
            useN = false;
        }
        return filter;
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

    public static void sort() throws InterruptedException {
        int operation;
        while (true) {
            System.out.println("Меню:\n 1)Сортировать по возрастанию\n 2) Сортировать по убыванию\n 3)Выход\n");
            operation = inputOperation();
            switch (operation) {
                case 1 -> {
                    sort = 1;
                    sort1();
                    cancelSort = false;
                    System.out.println("Данные отсортированы)");
                    return;
                }
                case 2 -> {
                    sort = 2;
                    sort2();
                    cancelSort = false;
                    System.out.println("Данные отсортированы)");
                    return;
                }
                case 3 -> {
                    return;
                }

            }
        }
    }

    public static void sort1() throws InterruptedException {
        SortAscending sortAscending = new SortAscending();
        sortAscending.threadSort.join();
        all3.clear();
        all3.addAll(all2);
    }

    public static void sort2() {
        SortDecreasing sortDecreasing = new SortDecreasing();
        sortDecreasing.start();
    }

    public static void cancelSorting() throws InterruptedException {
        all3.clear();
        all3.addAll(all);
        useW = false;
        useN = false;
        cancelSort = true;
        System.out.println("Сортировка отменена)");
        useDeleteFilter(null, useWeight, useName, true);
    }
}


