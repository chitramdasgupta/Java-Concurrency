package synchronizedcollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    private static final int QUEUE_CAPACITY = 10;
    private static final BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args) {
        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    // If space available then insert the element else wait
                    taskQueue.put(i);
                    System.out.println("Task produced " + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Consumer thread
        Thread consumerOne = new Thread(() -> {
            while (true) {
                try {
                    // if head present then retrieve and remove it else wait
                    int task = taskQueue.take();
                    processTask(task, "ConsumerOne");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumerTwo = new Thread(() -> {
            while (true) {
                try {
                    // if head present then retrieve and remove it else wait
                    int task = taskQueue.take();
                    processTask(task, "consumerTwo");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producer.start();
        consumerOne.start();
        consumerTwo.start();
    }

    private static void processTask(int task, String consumer) throws InterruptedException {
        System.out.println("Task " + task + " being processed by " + consumer);
        Thread.sleep(1000);
        System.out.println("Task consumed by " + consumer);
    }
}