package CP_Lab5.q2;

import java.util.concurrent.*;

public class Counter {

    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void adding() {
        this.counter++;
    }

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Counter counter = new Counter();
        IncrementCounter incrementCounter = new IncrementCounter(counter);
        Callable<Boolean> checkCounterCallable = new CheckCounter(counter, 5000);

        Future<Boolean> future = executorService.submit(checkCounterCallable);
        while (!future.get()) {
            executorService.submit(incrementCounter);
            future = executorService.submit(checkCounterCallable);
        }
        
        executorService.shutdown();
        System.out.println("Program Ends");

    }

}
