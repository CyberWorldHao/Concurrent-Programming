/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CP_Exam;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author CWH
 */
public class matrices {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        int m1 = 3;
        int n1 = 3;
        int m2 = 3;
        int n2 = 3;
        if (m1 != m2 || n1 != n2) {
            System.out.println("the two matrices must have the same dimensions and the same/compatible types of elements");
        } else {
            Random r = new Random();

            double[][] matric1 = new double[m1][n1];
            double[][] matric2 = new double[m2][n2];
            for (int i = 1; i <= m1; i++) {
                for (int j = 1; j <= n1; j++) {
                    matric1[i - 1][j - 1] = 9 * r.nextDouble();
                    matric2[i - 1][j - 1] = 9 * r.nextDouble();
                }
            }

            for (int i = 1; i <= m1; i++) {
                for (int j = 1; j <= n1; j++) {
                    System.out.print(matric1[i - 1][j - 1] + " ");
                }
                System.out.println();
            }
            System.out.println("   +   ");
            for (int i = 1; i <= m1; i++) {
                for (int j = 1; j <= n1; j++) {
                    System.out.print(matric2[i - 1][j - 1] + " ");
                }
                System.out.println();
            }

            executorService.execute(new additionMatrices(matric1, matric2));

            executorService.shutdown();
            while (true) {
                if (executorService.isTerminated()) {
                    System.out.println();
                    System.out.println("Program End");
                    break;
                }
            }
        }

    }

    public static class additionMatrices implements Runnable {

        private double[][] matric1;
        private double[][] matric2;
        private double[][] result;

        public additionMatrices(double[][] matric1, double[][] matric2) {
            this.matric1 = matric1;
            this.matric2 = matric2;
            this.result = new double[matric1.length][matric1[0].length];
        }

        @Override
        public void run() {

            for (int i = 1; i <= matric1.length; i++) {
                for (int j = 1; j <= matric1[i - 1].length; j++) {
                    result[i - 1][j - 1] = matric1[i - 1][j - 1] + matric2[i - 1][j - 1];
                }
            }
            System.out.println();
            System.out.println("The matric result is ");
            System.out.println();
            for (int i = 1; i <= matric1.length; i++) {
                for (int j = 1; j <= matric1[i - 1].length; j++) {
                    System.out.print(result[i - 1][j - 1] + " ");
                }
                System.out.println();
            }
        }
    }
}
