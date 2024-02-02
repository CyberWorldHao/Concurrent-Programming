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
public class Account {

    // Initial account balance
    static double balance = 1000;

    static synchronized void deposit(double deposit) {
        System.out.println("Balance before deposit : " + balance);
        System.out.println("Deposited : " + deposit);
        balance = balance + deposit;
        System.out.println("Balance after deposit: " + balance);
        System.out.println("");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static synchronized void withdrawal(double withdrawal) {
        if (balance >= withdrawal) {
            System.out.println("Balance before withdrawal : " + balance);
            System.out.println("withdrawal " + withdrawal);
            balance = balance - withdrawal;
            System.out.println("Balance after withdrawal: " + balance);
            System.out.println("");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Not enough balance for withdraw, balance : " + balance);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ThreadWithdrawal extends Thread {

    Account acc;
    double amount;

    ThreadWithdrawal(Account acc, double amount) {
        this.acc = acc;
        this.amount = amount;
    }

    public void run() {
        acc.withdrawal(this.amount);
    }
}

class ThreadDeposit extends Thread {

    Account acc;
    double amount;

    ThreadDeposit(Account acc, double amount) {
        this.acc = acc;
        this.amount = amount;
    }

    public void run() {
        acc.deposit(this.amount);
    }
}
