/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoMutualExclusionAndLocking;

/**
 *
 * @author CWH
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class Consumer1 implements Runnable {

    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

    public Consumer1(BlockingQueue<Integer> queue) {
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
