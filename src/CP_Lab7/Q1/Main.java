/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CP_Lab7.Q1;

/**
 *
 * @author CWH
 */
public class Main {

    interface MathOperation {

        int operation(int argument1, int argument2);
    }

    public static void main(String[] args) {
        MathOperation addition = (a1, a2) ->  {
            System.out.print("Addition Math Operation : " + a1 + " + " + a2 + " = ");
            return a1 + a2;
        };
        MathOperation subtraction = (a1, a2) -> {
            System.out.print("Subtraction Math Operation : " + a1 + " - " + a2 + " = ");
            return a1 - a2;
        };
        MathOperation multiplication = (a1, a2) -> {
            System.out.print("Multiplication Math Operation : " + a1 + " x " + a2 + " = ");
            return a1 * a2;
        };
        MathOperation division = (a1, a2) -> {
            if (a2 != 0) {
                System.out.print("Division Math Operation : " + a1 + " / " + a2 + " = ");
                return a1 / a2;
            } else {
                System.out.print("Can't divide by ");
                return 0;
            }
        };
        System.out.println(addition.operation(1, 7));
        System.out.println(subtraction.operation(100, 12));
        System.out.println(multiplication.operation(21, 8));
        System.out.println(division.operation(7992, 9));

    }
}
