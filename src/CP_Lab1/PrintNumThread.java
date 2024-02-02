/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CP_Lab1;

import java.util.Scanner;

/**
 *
 * @author CWH
 */
public class PrintNumThread extends Thread {

    private int num;
    private static int counter = 1;
    static Object sharedObject = new Object();

    PrintNumThread(int num) {
        this.num = num;
    }

    public void run() {
        synchronized (sharedObject) {
            while (this.counter <= this.num) {
                System.out.println(this.counter);
                this.counter++;
                sharedObject.notify();
                try {
                    sharedObject.wait(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
