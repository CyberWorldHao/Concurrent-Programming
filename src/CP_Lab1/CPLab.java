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
public class CPLab extends Thread {

    public static void main(String[] args) throws InterruptedException {

//        Runnable PrintChar = new PrintChar();
//        Runnable PrintChar2 = new PrintChar();
//        Runnable PrintNum = new PrintNum();
//     
//        Thread thread1 = new Thread(PrintChar);
//        Thread thread2 = new Thread(PrintNum);
//        Thread thread3 = new Thread(PrintChar2);
//      
//        thread1.start();
//        thread2.start();
//        thread3.start();
        PrintNumThread PrintNumT = new PrintNumThread(15);
        PrintCharThread PrintCharT = new PrintCharThread('a', 10);

        PrintNumT.start();
        PrintCharT.start();
    }

}
