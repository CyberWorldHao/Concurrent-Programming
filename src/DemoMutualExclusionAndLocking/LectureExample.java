/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoMutualExclusionAndLocking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author CWH
 */
public class LectureExample {

    public static void main(String[] args) {
        Collection<Integer> syncCollection = Collections.synchronizedCollection(new ArrayList<>());
        Runnable listOperations = () -> {
            syncCollection.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
            System.out.println(Thread.currentThread().getName() + ": Items added to the list");
            for (int element : syncCollection) {
//                System.out.println(element);
                System.out.println(Thread.currentThread().getName() + " " + element);
            }
            System.out.println("Number of elements in the collection: " + syncCollection.size());
        };

        Thread thread1 = new Thread(listOperations);
        Thread thread2 = new Thread(listOperations);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {

        }

    }
}
