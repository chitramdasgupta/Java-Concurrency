package threadsynchronization;

public class WaitAndNotify {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                one();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                two();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }

    private static void one() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Hello from method 1");
            lock.wait();
            System.out.println("Bye from method 1");
        }
    }

    private static void two() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Hello from method 2");
            lock.notify();
            System.out.println("Bye from method 2");
        }
    }
}
