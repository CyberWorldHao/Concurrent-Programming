package CP_Lab8.Q2;

import java.util.stream.IntStream;

public class Q2 {

    public static void main(String[] args) {
        int count = (int) IntStream.rangeClosed(1, 50).parallel().filter(
                num -> {
                    System.out.format("Filter : %d [%s]\n", num, Thread.currentThread().getName());
                    return isPrime(num);
                }
        ).count();

        System.out.println("Number of prime number between 1 and 50: " + count);
    }

    public static boolean isPrime(int number) {
        return number > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(number)).noneMatch(i -> number % i == 0);
    }
}
