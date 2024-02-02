/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CP_Lab1;

import java.util.Scanner;

/**
 *
 * @author CWH
 */
public class PrintChar implements Runnable {

        public void run() {
            Scanner s = new Scanner(System.in);
            System.out.print("Please enter character to print: ");
            String y = s.nextLine();
            System.out.print("Please enter number of time to print the char: ");
            String x = s.nextLine();
            int times = Integer.parseInt(x);
            for (int i = 0; i < times; i++) {
                System.out.println(y);

            }
        }
    }
