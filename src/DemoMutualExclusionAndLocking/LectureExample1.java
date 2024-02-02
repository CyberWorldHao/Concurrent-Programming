/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoMutualExclusionAndLocking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CWH
 */
public class LectureExample1 {

    public static void main(String[] args) {
        List<String> syncCollection = Collections.synchronizedList(Arrays.asList("a", "b", "c"));
        List<String> uppercasedCollection = new ArrayList<>();
        Runnable listOperations = () -> {
            synchronized (syncCollection) {
                syncCollection.forEach((e) -> {
                    uppercasedCollection.add(e.toUpperCase());
                });
            }
            System.out.println("Number of elements in the collection: " + uppercasedCollection.size());
            for (String element : uppercasedCollection) {
                System.out.println(element);
            }
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
