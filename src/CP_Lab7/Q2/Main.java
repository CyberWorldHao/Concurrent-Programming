/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CP_Lab7.Q2;

import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void evaluateArray(int[] arrayOfInteger, Predicate<Integer> predicate) {
        for (int integer : arrayOfInteger) {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        }
    }

    public static void evaluateList(List<Integer> listOfInteger, Predicate<Integer> predicate) {
        listOfInteger.stream().filter(predicate).forEach(System.out::println);
    }

    public static void main(String[] args) {
        Predicate<Integer> predicate;// Creating predicate
        int[] arrayOfInteger = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> listOfInteger = List.of(11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        System.out.println("Array of Integer");
        System.out.println("All the elements : ");
        predicate = argument -> (argument == (int) argument);
        evaluateArray(arrayOfInteger, predicate);
        System.out.println();

        System.out.println("All the odd elements : ");
        predicate = argument -> (argument % 2 != 0);
        evaluateArray(arrayOfInteger, predicate);
        System.out.println();

        System.out.println("All the even elements : ");
        predicate = argument -> (argument % 2 == 0);
        evaluateArray(arrayOfInteger, predicate);
        System.out.println();

        System.out.println("All the elements that are greater than 5 : ");
        predicate = argument -> (argument > 5);
        evaluateArray(arrayOfInteger, predicate);
        System.out.println();

        System.out.println("List of Integer");
        System.out.println("All the elements : ");
        predicate = argument -> (argument == (int) argument);
        evaluateList(listOfInteger, predicate);
        System.out.println();

        System.out.println("All the odd elements : ");
        predicate = argument -> (argument % 2 != 0);
        evaluateList(listOfInteger, predicate);
        System.out.println();

        System.out.println("All the even elements : ");
        predicate = argument -> (argument % 2 == 0);
        evaluateList(listOfInteger, predicate);
        System.out.println();

        System.out.println("All the elements that are greater than 5 : ");
        predicate = argument -> (argument > 5);
        evaluateList(listOfInteger, predicate);
        System.out.println();
    }

}
