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
public class ThreadsGenerator {

    static int finalLargest = -1;
    private static int numberOfThreads;
    private static int eachChunkOfLength;
    private static int arrayOfLength;
    private static int[] arrayOfIntegers;
    private static ThreadFindLargest[] threads;

    ThreadsGenerator(int numberOfThreads, ThreadFindLargest[] threads, int[] arrayOfIntegers) {
        this.numberOfThreads = numberOfThreads;
        this.arrayOfLength = arrayOfIntegers.length;
        this.arrayOfIntegers = arrayOfIntegers;
        this.eachChunkOfLength = this.arrayOfLength / this.numberOfThreads;
        this.threads = threads;
    }

    public void run() throws InterruptedException {

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new ThreadFindLargest((i * eachChunkOfLength), ((i + 1) * eachChunkOfLength), arrayOfIntegers);
        }

        Timer timer = new Timer();
        timer.startTimer();

        for (ThreadFindLargest thread : threads) {
            thread.start();
        }

        for (ThreadFindLargest thread : threads) {
            thread.join();
        }

        for (ThreadFindLargest thread : threads) {
            if (thread.getLargest() > finalLargest) {
                finalLargest = thread.getLargest();
            }
        }

        timer.stopTimer();
        timer.getTimeTaken();
        timer.getTimeTakeninMiliSec();

        System.out.println("The final largest value is : " + finalLargest);
        System.out.println("Time taken in nanosec : " + timer.getTimeTaken());
        System.out.println("Time taken in milisec : " + timer.getTimeTakeninMiliSec());
    }

    public static void main(String[] args) throws InterruptedException {

        int arrayOfLength = 1000000;
        int numberOfThreadsToBeGenerate = 4;
        int[] arrayOfIntegers = new int[arrayOfLength];
        Random random = new Random();
        for (int i = 0; i < arrayOfLength; i++) {
            arrayOfIntegers[i] = random.nextInt(50000) + 1;
        }
        ThreadFindLargest[] threads = new ThreadFindLargest[numberOfThreadsToBeGenerate];

        ThreadsGenerator multiThreads = new ThreadsGenerator(numberOfThreadsToBeGenerate, threads, arrayOfIntegers);
        multiThreads.run();

    }

}
