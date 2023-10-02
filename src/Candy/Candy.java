package Candy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Candy {
    static double[] weightt = new double[4];
    public void makeGift() {
        Scanner sc = new Scanner(System.in);

        All biscuit = new Biscuit();
        All chocolate = new Chocolate();
        All sweet = new Sweet();
        All marshmallow = new Marshmallow();

        ArrayList<All> all = new ArrayList<>();

        int operation;

        System.out.println("Использовать старое меню?\n 1)Да\n 2)Нет");
        while(true) {
            try {
                operation = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Введите число: ");
                sc.next();
            }
        }
        if(operation ==1) {
            try {
                all = Serializator.deserialization();
            }catch(IOException | ClassNotFoundException e){
                System.err.println("Ошибка ввода-вывода\n");
            }
        }
        while (true) {
            System.out.println("Выберите тип сладостей:\n 1) Печенье\n 2) Шоколад\n 3) Зефир\n 4) Конфеты\n 5) Выход");
            try {
                operation = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }

            switch (operation) {
                case 1-> weightt[0] += biscuit.choose(all);
                case 2-> weightt[1] += chocolate.choose(all);
                case 3-> weightt[2] += marshmallow.choose(all);
                case 4->weightt[3] += sweet.choose(all);
                case 5-> {
                    return;
                }
            }
        }
    }
    public  void count() {
        Scanner sc = new Scanner(System.in);
        Calculate result = ((n) -> {
            double w1 = 0;
            for (int i = 0; i < 4; i++)
                w1 += n[i];
            return w1;
        });

        if(result.func(weightt) == 0) {
            System.out.println("Вы еще не добавили сладости в подарок.Желаете добавить?\n 1)Да\n 2)Нет\n");
            while(true){
                int operation = 0;
                try{
                    operation = sc.nextInt();
                    if(operation == 1) {
                       makeGift();
                        return;
                    }
                    else if(operation ==2)
                        break;
                }catch(InputMismatchException e){
                    System.out.print("Повторите ввод: ");
                }
            }
        }
        else
            System.out.println("Общий вес подарка: " + result.func(weightt));
    }

    public void view(){
        boolean t = true;
        for(int i = 0; i< 4; i++){
            if(weightt[i] != 0){
                t = false;
                switch (i){
                    case 0:
                        All biscuit = new Biscuit();
                        biscuit.viewGift();
                        break;
                    case 1:
                        All cake = new Chocolate();
                        cake.viewGift();
                        break;
                    case 2:
                        All marshmallow = new Marshmallow();
                        marshmallow.viewGift();
                        break;
                    case 3:
                        All sweet = new Sweet();
                        sweet.viewGift();
                        break;
                }
                System.out.println("Общий вес: "+ weightt[i]+"\n");
            }

        }
        if(t) System.out.println("Вы не собрали подарок");
    }
}

