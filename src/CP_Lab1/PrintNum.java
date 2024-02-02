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
public class PrintNum implements Runnable {

    public void run() {
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter number to print: ");
        String x = s.nextLine();
        int num = Integer.parseInt(x);
        for (int i = 1; i <= num; i++) {
            System.out.println(i);

        }
    }
}
