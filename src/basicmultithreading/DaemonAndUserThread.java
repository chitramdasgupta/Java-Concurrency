package basicmultithreading;

public class DaemonAndUserThread {
    public static void main(String[] args) {
        // Create a daemon thread
        Thread one = new Thread(new DaemonThread());
        one.setDaemon(true);

        // Create a user thread
        Thread two = new Thread(new UserThread());

        one.start();
        two.start();
    }
}

// The daemon thread will execute a print statement after every 1 second
class DaemonThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Daemon thread is running...");
        }
    }
}

// The user thread will execute a print statement after 5 seconds
class UserThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("User thread done with its execution");
    }
}