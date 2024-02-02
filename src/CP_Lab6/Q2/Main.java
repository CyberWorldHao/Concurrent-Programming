package CP_Lab6.Q2;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // The more complex the task is, the more different in time we can see

        int num1 = 38;
        int num2 = 38;
        ArrayList<Timer> timerArrayList = new ArrayList<>();

        System.out.println("*** Sequential Method ***");
        Timer timer1 = startComputing(num1);
        Timer timer2 = startComputing(num2);
        timerArrayList.add(timer1);
        timerArrayList.add(timer2);
        long totalCalculationTimeSequentialMethod = displayResult(timerArrayList);

        timerArrayList.clear();

        System.out.println();
        System.out.println();
        System.out.println("*** CompletableFuture Method ***"); // faster with more complex task
        CompletableFuture<Timer> futureTimer4 = CompletableFuture.supplyAsync(() -> startComputing(num1));
        CompletableFuture<Timer> futureTimer5 = CompletableFuture.supplyAsync(() -> startComputing(num2));
        Timer timer4 = futureTimer4.get();
        Timer timer5 = futureTimer5.get();
        timerArrayList.add(timer4);
        timerArrayList.add(timer5);
        long totalCalculationTimeCompletableFutureMethod = displayResult(timerArrayList);

        if (totalCalculationTimeCompletableFutureMethod < totalCalculationTimeSequentialMethod) {
            System.out.println();
            System.out.println("******************************************");
            System.out.println("*** CompletableFuture Method Is Faster ***");
            System.out.println("******************************************");
        } else if (totalCalculationTimeCompletableFutureMethod > totalCalculationTimeSequentialMethod) {
            System.out.println();
            System.out.println("*****************************************");
            System.out.println("****** Sequential Method Is Faster ******");
            System.out.println("*****************************************");
        } else {
            System.out.println();
            System.out.println("****************************************");
            System.out.println("***************** Draw *****************");
            System.out.println("****************************************");
        }
    }

    public static int fib(int num) {
        if (num == 0) {
            return 0;
        } else if (num == 1 || num == 2) {
            return 1;
        }

        return fib(num - 1) + fib(num - 2);

    }

    public static Timer startComputing(int num) {
        Timer timer = new Timer();
        timer.start();
        int ans = fib(num);
        timer.finish();

        System.out.println("Fib(" + num + ") : " + ans);
        calculateIndividualTime(timer);

        return timer;
    }

    public static void calculateIndividualTime(Timer timer) {
        long startTime = timer.getStartTime();
        long endTime = timer.getEndTime();

        System.out.println("Start Time : " + startTime + " ms");
        System.out.println("End Time : " + endTime + " ms");
        System.out.println("Total time : " + (endTime - startTime) + " ms");
        System.out.println();
    }

    public static long displayResult(ArrayList<Timer> timers) {
        long earliestTime = timers.get(0).getStartTime();
        long latestTime = timers.get(0).getEndTime();

        for (Timer timer : timers) {
            if (timer.getStartTime() < earliestTime) {
                earliestTime = timer.getStartTime();
            }
            if (timer.getEndTime() > latestTime) {
                latestTime = timer.getEndTime();
            }
        }
        System.out.println("============================================");
        System.out.println("Earliest Start Time : " + earliestTime + " ms");
        System.out.println("Latest End Time : " + latestTime + " ms");
        System.out.println("Total Calculation Time : " + (latestTime - earliestTime) + " ms");
        System.out.println("============================================");

        return (latestTime - earliestTime);
    }

}
