package executorservice;

import java.util.concurrent.*;

public class CallableTask {
    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            Future<Integer> res = executorService.submit(new TaskWithReturnValue());

            System.out.println(res.get(10, TimeUnit.SECONDS));
            System.out.println("Main thread completed");
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}

class TaskWithReturnValue implements Callable<Integer> {
    @Override
    public Integer call() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 100;
    }
}