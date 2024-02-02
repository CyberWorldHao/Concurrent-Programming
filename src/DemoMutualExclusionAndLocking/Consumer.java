package DemoMutualExclusionAndLocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class Consumer implements Runnable {

    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        for (int i = 0; i < 9; i++) {
            try {

                System.out.println("Consumed: " + queue.take() + " Queue size : " + queue.size());
                Thread.sleep(200);

            } catch (InterruptedException ex) {
                System.out.println("Consumer is interrupted.");
            }
        }
    }
}




