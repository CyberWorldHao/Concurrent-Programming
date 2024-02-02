/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CP_Lab2;

/**
 *
 * @author CWH
 */
public class ThreadFindLargest extends Thread {

    private int arrayLoopStartPoint;
    private int arrayLoopEndPoint;
//    private static int arrayLoopStartPoint;
//    private static int arrayLoopEndPoint;
    private static int[] arrayOfIntegers;
    private static int largest = -1;

    ThreadFindLargest(int arrayLoopStartPoint, int arrayLoopEndPoint, int[] arrayOfIntegers) {
        this.arrayLoopStartPoint = arrayLoopStartPoint;
        this.arrayLoopEndPoint = arrayLoopEndPoint;
        this.arrayOfIntegers = arrayOfIntegers;
    }

    public void run() {
        for (int j = arrayLoopStartPoint; j < arrayLoopEndPoint; j++) {
            if (arrayOfIntegers[j] > largest) {
                largest = arrayOfIntegers[j];
            }
        }
    }

    public int getLargest() {
        return largest;
    }

}
