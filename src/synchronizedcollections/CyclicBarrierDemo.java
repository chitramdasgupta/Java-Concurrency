package synchronizedcollections;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static final int NUM_TOURISTS = 5;
    private static final int NUM_STAGES = 3;
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM_TOURISTS, () -> {
        System.out.println("Tour guide starts speaking...");
    });

    public static void main(String[] args) {
        for (int i = 0; i < NUM_TOURISTS; i++) {
            Thread tourist = new Thread(new Tourist(i));
            tourist.start();
        }
    }

    private static class Tourist implements Runnable {
        private final int touristId;

        Tourist(int touristId) {
            this.touristId = touristId;
        }

        // 1. When a thread reaches the barrier and calls `await()` it blocks its execution until all other party threads
        // have also called `await()`.
        // 2. After all the threads calls `await()` the barrier is tripped. All the party threads (that have already called
        // `await()`) are released. Then the barrier action is also executed.
        @Override
        public void run() {
            for (int i = 0; i < NUM_STAGES; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Tourist: " + touristId + " arrives at stage " + i);

                // wait for all tourists to arrive at the current stage
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}