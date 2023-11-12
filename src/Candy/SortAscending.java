package Candy;

import java.util.Collection;
import java.util.Comparator;

import static Candy.Candy.all2;

public class SortAscending implements Runnable {
    Thread threadSort;
    SortAscending(){
        threadSort = new Thread(this);
        threadSort.start();
    }
    @Override
    public void run() {
        Comparator<All> byWeight = Comparator.comparing(All::getWeight);
        all2.sort(byWeight);
    }
}
