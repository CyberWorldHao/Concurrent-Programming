/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoForkOrJoinFramework;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author Chiew
 */
public class ParallelMergeSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int SIZE = 1000000;
        int[] list1 = new int[SIZE];
        int[] list2 = new int[SIZE];

        for (int i = 0; i < list1.length; i++) {
            list1[i] = list2[i] = (int) (Math.random() * 10000000);
        }

        long startTime = System.currentTimeMillis();
        parallelMergeSort(list1);
        long endTime = System.currentTimeMillis();
        System.out.println("\nParallel merge sort with " + Runtime.getRuntime().availableProcessors()
                + " processors completed in " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        MergeSort.mergeSort(list2);
        endTime = System.currentTimeMillis();
        System.out.println("\nSequential merge sort completed in " + (endTime - startTime) + " milliseconds");
    }

    public static void parallelMergeSort(int[] list) {
        RecursiveAction mainTask = new SortTask(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class SortTask extends RecursiveAction {

        private final int THRESHOLD = 500;
        private int[] list;

        SortTask(int[] list) {
            this.list = list;
        }

        @Override
        protected void compute() {
            if (list.length < THRESHOLD) {
                java.util.Arrays.sort(list);
            } else {
                int[] firstHalf = new int[list.length / 2];
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

                int secondHalfLength = list.length - list.length / 2;
                int[] secondHalf = new int[secondHalfLength];
                System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);

                invokeAll(new SortTask(firstHalf), new SortTask(secondHalf));

                MergeSort.merge(firstHalf, secondHalf, list);
            }
        }
    }

    private static class MergeSort {

        public static void mergeSort(int[] list) {
            if (list.length > 1) {
                int[] firstHalf = new int[list.length / 2];
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
                mergeSort(firstHalf);

                int secondHalfLength = list.length - list.length / 2;
                int[] secondHalf = new int[secondHalfLength];
                System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
                mergeSort(secondHalf);

                merge(firstHalf, secondHalf, list);
            }
        }

        public static void merge(int[] list1, int[] list2, int[] temp) {
            int current1 = 0;
            int current2 = 0;
            int current3 = 0;
            
            while (current1 < list1.length && current2 < list2.length) {
                if (list1[current1] < list2[current2])
                    temp[current3++] = list1[current1++];
                else
                    temp[current3++] = list2[current2++];
            }
            
            while (current1 < list1.length)
                temp[current3++] = list1[current1++];
            
            while (current2 < list2.length)
                temp[current3++] = list2[current2++];
        }
    }
}