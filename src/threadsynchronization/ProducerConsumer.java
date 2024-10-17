package threadsynchronization;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    public static void main(String[] args) {
        Worker worker = new Worker();

        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}

class Worker {
    private final static Object lock = new Object();

    private int number = 0;
    private final int limit = 5;
    private final List<Integer> sharedBuffer = new ArrayList<>();

    public void produce() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (sharedBuffer.size() >= limit) {
                    System.out.println("Shared buffer is full");
                    number = 0;
                    lock.wait();
                } else {
                    sharedBuffer.add(number);
                    System.out.println(number + " produced");
                    ++number;
                    lock.notify();
                }

                Thread.sleep(250);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (sharedBuffer.isEmpty()) {
                    System.out.println("Shared buffer is empty");
                    lock.wait();
                } else {
                    System.out.println(sharedBuffer.removeFirst() + " consumed");
                    lock.notify();
                }

                Thread.sleep(250);
            }
        }
    }
}
