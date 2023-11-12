package Candy;

import java.util.Comparator;

import static Candy.Candy.all2;
import static Candy.Candy.all3;

public class SortDecreasing extends Thread {
    @Override
    public void run() {
        Comparator<All> byWeight = Comparator.comparing(All::getWeight).reversed();
        all2.sort(byWeight);
        all3.clear();
        all3.addAll(all2);
    }
}
