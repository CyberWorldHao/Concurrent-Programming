/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CP_Lab3;

/**
 *
 * @author CWH
 */
public class Q2 {

    public static void main(String[] args) throws InterruptedException {

        Account acc = new Account();
        ThreadWithdrawal t1 = new ThreadWithdrawal(acc, 67);
        ThreadWithdrawal t2 = new ThreadWithdrawal(acc, 69);
        ThreadDeposit t3 = new ThreadDeposit(acc, 444);
        ThreadWithdrawal t4 = new ThreadWithdrawal(acc, 300);
        ThreadWithdrawal t5 = new ThreadWithdrawal(acc, 260);
        ThreadDeposit t6 = new ThreadDeposit(acc, 888);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
