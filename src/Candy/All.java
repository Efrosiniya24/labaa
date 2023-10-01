package Candy;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class All {
    protected String name;
    protected double weight;
    public abstract void addGift(int i);
    public abstract double choose();
    public abstract void view();
    public abstract void addCandy();
    public abstract void viewGift();
    public abstract void serializationCandy() throws IOException;
    public abstract void deserializationCandy() throws IOException, ClassNotFoundException;
    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите название: ");
        try {
            this.name = sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.print("Ошибка ввода( Повторите ввод: ");
        }

        System.out.print("Введите массу: ");
        try {
            this.weight = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.print("Ошибка ввода( Повторите ввод: ");
        }
        sc.nextLine();
    }
}
