package CP_Lab5.q2;

public class IncrementCounter implements Runnable {

    private Counter counter;

    public IncrementCounter(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println("Current counter value : " + counter.getCounter());
        counter.adding();
        System.out.println("Increase counter value by 1");
        try {
            System.out.println("Sleeps for 20ms after each increment");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
