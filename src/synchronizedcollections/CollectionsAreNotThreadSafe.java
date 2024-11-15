package synchronizedcollections;

import java.util.ArrayList;
import java.util.List;

public class CollectionsAreNotThreadSafe {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> numbers = new ArrayList<>();

        Thread one = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                numbers.add(i);
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                numbers.add(i);
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println(numbers.size());
    }
}