package CP_Lab5.q2;

import java.util.concurrent.Callable;

public class CheckCounter implements Callable<Boolean> {

    public Counter counter;
    private volatile int maxValue;

    public CheckCounter(Counter counter, int maxValue) {
        this.counter = counter;
        this.maxValue = maxValue;
    }

    @Override
    public Boolean call() throws Exception {
        if (counter.getCounter() >= maxValue) { // large and equal due to two thread may exist
            System.out.println("The counter value is " + counter.getCounter() + " has reach maximum value.");
            return true;
        }
        return false;
    }
}
