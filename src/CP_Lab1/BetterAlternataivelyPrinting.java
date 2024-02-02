/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CP_Lab1;

/**
 *
 * @author CWH
 */
public class BetterAlternataivelyPrinting {

    private static int count = 0;
    private static int numberToPrint = 15;
    private static int timeToPrint = 10;
    private static char charToPrint = 'A';
    private static Object sharedObject = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i < numberToPrint + 1; i++) {
                    synchronized (sharedObject) {
                        System.out.println(i + " by: " + Thread.currentThread().getName());
                        sharedObject.notify();
                        if (count <= 0) {
                            try {
                                sharedObject.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        if (i == numberToPrint - 1) {
                            count++;
                            sharedObject.notify();
                        };
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < timeToPrint; i++) {
                    synchronized (sharedObject) {
                        System.out.println(charToPrint + " by: " + Thread.currentThread().getName());
                        sharedObject.notify();
                        if (count <= 0) {
                            try {
                                sharedObject.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        if (i == timeToPrint - 1) {
                            count++;
                            sharedObject.notify();
                        };
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
