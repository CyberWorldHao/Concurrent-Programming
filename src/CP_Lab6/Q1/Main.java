package CP_Lab6.Q1;


//Their difference in usage can be seen from their respective documentation:

//Callable:
//A task that returns a result and may throw an exception. Implementors define a single method 
//with no arguments called call.
//The Callable interface is similar to Runnable, in that both are designed 
//for classes whose instances are potentially executed by another thread.

//Supplier:
//Represents a supplier of results.
//There is no requirement that a new or distinct result be returned each time the supplier is invoked.

//This means that the caller of Callable.call expects an exception to be thrown 
//and will handle the exception accordingly. 
//This is useful for tasks like reading and writing to files, where many kinds of IOExceptions can be thrown. 
//Callable is also designed to be run on another thread.
//Supplier on the other hand, is very general. 
//It just "supplies a value" and that's it.
//So Callable is more specialised than Supplier. If you are not dealing with 
//another thread or your task is very unlikely to throw an exception, Supplier is recommended.
//https://stackoverflow.com/questions/52215917/callable-vs-supplier-interface-in-java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        callableMethod(2500);
        callableMethod(111111);
        completeableMethhod(2500);
        completeableMethhod(111111);

    }

    public static void callableMethod(int max) throws ExecutionException, InterruptedException {
        int range = max;
        Integer[] integers = new Integer[range];
        ArrayList<ArrayList<Integer>> arrayListsOfLargestDivisor = new ArrayList<>();

        for (int i = 0; i < range; i++) {
            // get every integer from 1 to the integer input
            integers[i] = i + 1;
        }

        // Creates a thread pool that creates new threads as needed, 
        // but will reuse previously constructed threads when they are available
        ExecutorService executorService = Executors.newCachedThreadPool();
        int start = 0;
        
        // Create a Callable instance
        Callable<ArrayList<Integer>> numberOfDivisor;
        
        // Each sub-task processes at most 1000 integers. So divide by 1000
        for (int j = 0; j <= range / 1000; j++) {
            if (start + 999 < integers.length) {
                // if the number of integer is large than 1000 then need to redo again until cover all integers
                // copies the specified 1000 range of the array and pass into the Callable
                numberOfDivisor = new NumberOfDivisorCallable(Arrays.copyOfRange(integers, start, start + 1000));
                start = start + 999;
            } else {
                // Enter here means the number of integer is fully covered
                // copies the specified 1000 range of the array and pass into the Callable
                numberOfDivisor = new NumberOfDivisorCallable(Arrays.copyOfRange(integers, start, integers.length));
            }
            // Submits a value-returning task for execution and 
            // returns a Future representing the pending results of the task 
            // The Future's get method will return the task's result upon successful completion
            Future<ArrayList<Integer>> future = executorService.submit(numberOfDivisor);
            // Collect the arraylist to do compare later
            arrayListsOfLargestDivisor.add(future.get());
        }

        executorService.shutdown();

        while (true) {
            // Check the executorService whether is terminated
            // If terminated means the process is done
            if (executorService.isTerminated()) {
                break;
            }
        }

        int size = 0;
        int largestSizeNumber = 0;
        for (ArrayList arrayList : arrayListsOfLargestDivisor) {
            // loop through array list to get the list that have the largest number of divisors
            if (arrayList.size() > size) {
                size = arrayList.size();
                // Get the last value of the arraylist which is the number itself with size - 1 as index
                largestSizeNumber = (int) arrayList.get(arrayList.size()-1);
            }
        }
        System.out.println("In the range of 1 to " + range + ", " + largestSizeNumber + " has the most divisor of "
                + size);
    }

    // time consuming will prefer completeable due to it can run in background and no need blocking
    public static void completeableMethhod(int max) throws ExecutionException, InterruptedException {
        int range = max;
        Integer[] integers = new Integer[range];
        ArrayList<ArrayList<Integer>> arrayListsOfLargestDivisor = new ArrayList<>();

        for (int i = 0; i < range; i++) {
            // get every integer from 1 to the integer input
            integers[i] = i + 1;
        }

        // Creates a thread pool that creates new threads as needed, 
        // but will reuse previously constructed threads when they are available
        ExecutorService executorService = Executors.newCachedThreadPool();
        int start = 0;
        Supplier<ArrayList<Integer>> numberOfDivisor;
        for (int j = 0; j <= range / 1000; j++) {
            if (start + 999 < integers.length) { 
                // if the number of integer is large than 1000 then need to redo again until cover all integers
                // copies the specified 1000 range of the array and pass into the Supplier
                numberOfDivisor = new NumberOfDivisorSupplier(Arrays.copyOfRange(integers, start, start + 1000));
                start = start + 999;
            } else {
                // Enter here means the number of integer is fully covered
                // copies the specified 1000 range of the array and pass into the Supplier
                numberOfDivisor = new NumberOfDivisorSupplier(Arrays.copyOfRange(integers, start, integers.length));
            }
            // Returns a new CompletableFuture that is asynchronously completed by a task running 
            // in the given executor with the value obtained by calling the given Supplier
            CompletableFuture<ArrayList<Integer>> completableFuture = CompletableFuture.supplyAsync(numberOfDivisor, executorService);
            // Collect the arraylist to do compare later
            arrayListsOfLargestDivisor.add(completableFuture.get());
        }

        executorService.shutdown();

        int size = 0;
        int largestSizeNumber = 0;
        for (ArrayList arrayList : arrayListsOfLargestDivisor) { 
            // loop through array list to get the list that have the largest number of divisors
            if (arrayList.size() > size) {
                size = arrayList.size();
                // Get the last value of the arraylist which is the number itself with size - 1 as index
                largestSizeNumber = (int) arrayList.get(arrayList.size() - 1);
            }
        }
        System.out.println("In the range of 1 to " + range + ", " + largestSizeNumber + " has the most divisor of "
                + size);
    }

}
