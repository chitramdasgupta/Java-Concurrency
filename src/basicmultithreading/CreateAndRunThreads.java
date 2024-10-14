package basicmultithreading;

public class CreateAndRunThreads {
    public static void main(String[] args) {
        // Thread created by implementing the Runnable interface
        Thread threadOne = new Thread(new ThreadOne());
        // Thread created using a lambda expression, which is a compact way to implement the Runnable interface
        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        });

        // Thread creating using a class that inherits a parent class that implements the Runnable interface
        Thread threadThree = new ThreadThree();

        // Start the three threads. Starting a thread does not mean that the threads will start running right away,
        // rather it tells the JVM that this thread is ready to run
        threadOne.start();
        threadTwo.start();
        threadThree.start();

        // The threads run concurrently in an indeterminate order
        // The JVM's thread scheduler decides which thread to run at a given time

        // Note that there are actually 4 threads in this program: the main thread, and three custom ones
        System.out.println("The last line in the main method");
    }
}

class ThreadOne implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class ThreadThree extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}