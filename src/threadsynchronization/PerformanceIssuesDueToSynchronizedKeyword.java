package threadsynchronization;

public class PerformanceIssuesDueToSynchronizedKeyword {
    public static int counter1 = 0;
    public static int counter2 = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                incrementCounter1();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                incrementCounter2();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter 1: " + counter1);
        System.out.println("Counter 2: " + counter2);
    }

    private synchronized static void incrementCounter1() {
        counter1++;
    }

    private synchronized static void incrementCounter2() {
        counter2++;
    }
}
