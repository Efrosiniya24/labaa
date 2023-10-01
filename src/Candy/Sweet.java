package Candy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class Sweet extends All implements Serializable {
    static ArrayList<String> SweetName = new ArrayList<>();
    static ArrayList<Double> SweetWeight = new ArrayList<>();
    static ArrayList<String> SweetGift = new ArrayList<>();

    @Override
    public void addCandy() {
        SweetName.add(name);
        SweetWeight.add(weight);
    }
    @Override
    public void addGift(int i){
        boolean t = true;
        for (int u = 0; u < SweetGift.size(); u++)
            if(SweetGift.get(u) == SweetName.get(i-1))
                t =false;
        if(t == true)
        SweetGift.add(SweetName.get(i-1));
    }

    @Override
    public void view() {
        System.out.println("\n___Конфеты____: ");
             for (int i = 0; i < SweetName.size(); i++)
                 System.out.println((i + 1) + ") " + SweetName.get(i) + " вес: " + SweetWeight.get(i));
    }
    public void viewGift(){
        System.out.println("\n___Конфеты____: ");
        for (int i = 0; i < SweetGift.size(); i++)
            System.out.println((i + 1) + ") " + SweetGift.get(i));
    }


    @Override
    public double choose() {
        int operation = 0;
        int amount = 0;
        double allWeihgt = 0;

        if(SweetWeight.size() == 0 )
            System.out.println("Конфет нет");
        else {

            while (true) {
                System.out.print("Выберите конфеты");
                view();

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
                this.addGift(operation);
                allWeihgt += (amount * SweetWeight.get(operation - 1));

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
        Serializator.serialization(SweetWeight);
        Serializator.serialization(SweetName);
    }
    @Override
    public  void deserializationCandy() throws IOException, ClassNotFoundException {
        SweetName = Serializator.deserialization();
        SweetWeight = Serializator.deserialization();
    }
}
