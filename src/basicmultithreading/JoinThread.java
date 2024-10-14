package basicmultithreading;

public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        });
        Thread two = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        });

        System.out.println("Before starting the threads");
        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println("Done executing the threads");
    }
}
