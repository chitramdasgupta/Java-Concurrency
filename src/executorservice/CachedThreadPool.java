package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 10; i++) {
                executor.execute(new TaskOne(i));
            }
        }
    }
}

class TaskOne implements Runnable {
    private final int taskId;

    TaskOne(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task: " + taskId + " being executed by " + Thread.currentThread().getName());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
