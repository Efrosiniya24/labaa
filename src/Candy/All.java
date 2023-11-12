package Candy;

import java.io.Serializable;
import java.util.*;

public abstract class All implements Serializable {
    protected String name;
    protected double weight;
    public abstract void addGift(int i, All all);
    public abstract double choose(List<All> all);
    public abstract boolean view(List<All> all);
    public abstract void viewGift();
//    public All(String name) {
//        this.name = name;
//    }

    public All() {
    }
    public String getName() {
        return name;
    }
    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public String addName() {
        Scanner sc = new Scanner(System.in);
        String name = null;
        System.out.print("Введите название: ");
        try {
            name = sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.print("Ошибка ввода( Повторите ввод: ");
        }
        return name;
    }
    public double addWeight() {
        Scanner sc = new Scanner(System.in);
        double weight = 0;
        System.out.print("Введите массу: ");
        try {
            weight = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.print("Ошибка ввода( Повторите ввод: ");
        }
        sc.nextLine();
        return weight;
    }

    @Override
    public String toString() {
        return "All{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
