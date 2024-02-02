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
public class Timer {

    private static long startTime;
    private static long endTime;

    public void startTimer() {
        startTime = System.nanoTime();
    }
    
    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public long getTimeTaken() {
        return (endTime - startTime);
    }

    public long getTimeTakeninMiliSec() {
        return (endTime - startTime) / 1000000;
    }

}
