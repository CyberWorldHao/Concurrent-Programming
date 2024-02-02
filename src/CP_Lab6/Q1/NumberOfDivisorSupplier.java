package CP_Lab6.Q1;

import java.util.ArrayList;
import java.util.function.Supplier;

public class NumberOfDivisorSupplier implements Supplier<ArrayList<Integer>> {

    private Integer[] integerArray;
    private int divisorCounter;
    private ArrayList<Integer> divisorArray;

    public NumberOfDivisorSupplier(Integer[] integerArray) {
        this.integerArray = integerArray;
        this.divisorCounter = 0;
        divisorArray = new ArrayList<>();
    }

    @Override
    public ArrayList<Integer> get() {
        for (int i = 0; i < integerArray.length; i++) {
            // Passing in each integer to find the integer that have largest number of divisor
            findNumberOfDivisor(integerArray[i]);
        }

        return divisorArray;
    }

    private void findNumberOfDivisor(int number) {
        int countingDivisor = 0;
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                // calculate the divisor
                countingDivisor++;
                divisors.add(i);
            }
        }

        if (countingDivisor > divisorCounter) {
            // take the new number that is larger number of divisor
            divisorCounter = countingDivisor;
            divisorArray = divisors;
        }
    }

}
