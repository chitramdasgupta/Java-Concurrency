package threadsynchronization;

public class SynchronizeKeyword {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                incrementCounter();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                incrementCounter();
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println("The counter value is: " + counter);
    }

    private synchronized static void incrementCounter() {
        counter++;
    }
}
