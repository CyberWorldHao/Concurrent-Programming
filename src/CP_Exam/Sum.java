/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CP_Exam;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author CWH
 */
public class Sum extends Thread {

    //Declare instance variables here
    private int sum = 0;
    private int start;
    private int end;
    private static int[] subarray;

    //Initialise the instance variable
    public Sum(int param1, int param2, int[] param3) {
        start = param1;
        end = param2;
        subarray = param3;
    }

    //get the sum of this sub-array
    public synchronized void run() {
        for (int i = start; i < end; i++) {
            sum += subarray[i];
        }
    }

    //return the sum of this sub-array
    public int getSum() {
        return sum;
    }

    public static void main(String args[]) throws InterruptedException {

        final int numOfThreads = 3; // i change the numOfThreads to 3 to avoid decimal numbers for length of each subarray
        final int numOfElements = 75;
        int[] elements = new int[numOfElements];
        int correctSum = 0;

        //fill the array with values ranging 1-100
        Random random = new Random();
        for (int i = 0; i < numOfElements; i++) {
            elements[i] = random.nextInt(100) + 1;
            correctSum += elements[i];
            System.out.println("Array[" + (i + 1) + "] : " + elements[i]);
        }

        //Divide the array into numOfThreads sub-arrays
        int lengthOfSubArray = numOfElements / numOfThreads;
        System.out.println("The length of subarray " + lengthOfSubArray);

        //Create 3 instances of Sum class (s1-s3), one for
        //each sub-array
        Sum[] s = new Sum[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
//            s[i] = new Sum(0, 75, elements);
            s[i] = new Sum((i * lengthOfSubArray), ((i + 1) * lengthOfSubArray), elements);
        }

        //Using a loop, call s1.run(), s2.run(), s3.run(), one by one
        for (Sum thread : s) {
            thread.start();
        }

        for (Sum thread : s) {
            thread.join();
        }

        int sum = s[0].getSum() + s[1].getSum() + s[2].getSum();
        System.out.println("The sum of the subarray1 is " + s[0].getSum());
        System.out.println("The sum of the subarray2 is " + s[1].getSum());
        System.out.println("The sum of the subarray3 is " + s[2].getSum());
        System.out.println("The sum of the array is " + sum);
        System.out.println("The correct sum of the array is " + correctSum);

    }

}
