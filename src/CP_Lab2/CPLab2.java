/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CP_Lab2;

import java.util.Random;

/**
 *
 * @author CWH
 */
public class CPLab2 extends Thread {

    public static void main(String[] args) {
        int[] arrayOfIntegers = new int[1000000];
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            arrayOfIntegers[i] = random.nextInt(50000) + 1;
        }

        int largest = -1;
        Timer timer = new Timer();
        timer.startTimer();
        for (int i = 0; i < 1000000; i++) {
            if (arrayOfIntegers[i] > largest) {
                largest = arrayOfIntegers[i];
                System.out.println("Now the largest value is : " + largest);
            }
        }
        timer.stopTimer();
        System.out.println("The final largest value is : " + largest);
        System.out.println("Time taken in nanosec : " + timer.getTimeTaken());
        System.out.println("Time taken in milisec : " + timer.getTimeTakeninMiliSec());


    }
}
