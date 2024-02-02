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
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable {

    Random random = new Random();
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        for (int i = 0; i < 7; i++) {
            try {
                int num = random.nextInt(50);
                queue.put(num);
                System.out.println("Produced: " + num + " Queue size : " + queue.size());
                Thread.sleep(100);

            } catch (InterruptedException ex) {
                System.out.println("Producer is interrupted.");
            }
        }
    }
}
