package CP_Lab5.q1;

import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class StackAccess {

    private Stack<Integer> stack;
    private Condition emptyStackCondition;
    private Condition fullStackCondition;
    private Lock lock;

    private final int stackSize;

    public StackAccess(int stackSize) {
        this.stack = new Stack<Integer>();
        this.lock = new ReentrantLock();
        this.emptyStackCondition = this.lock.newCondition();
        this.fullStackCondition = this.lock.newCondition();
        this.stackSize = stackSize;
    }

    public void pop() {
        try {
            this.lock.lock();
            if (checkStackEmpty()) {
                try {
                    System.out.println();
                    System.out.println(Thread.currentThread().getName() + " popping");
                    System.out.println("The stack is empty waiting to pop");
                    System.out.println("Current Stack : " + stack.toString());
                    this.emptyStackCondition.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();

            //check again the stack if it is empty
            if (checkStackEmpty()) {
                System.out.println(Thread.currentThread().getName() + " popping");
                System.out.println("The stack is empty cannot pop");
                System.out.println("Current Stack : " + stack.toString());
            } else {
                System.out.println(Thread.currentThread().getName() + " popping");
                System.out.println("Value Pop Out : " + stack.pop());
                System.out.println("Current Stack : " + stack.toString());
                this.fullStackCondition.signalAll();
            }
        } finally {
            this.lock.unlock();
            System.out.println("Unlock " + Thread.currentThread().getName() + " from popping");

        }
    }

    public void push(int writeElement) {
        try {
            this.lock.lock();
            if (checkStackFull()) {
                try {
                    System.out.println();
                    System.out.println(Thread.currentThread().getName() + " pushing");
                    System.out.println("The stack is full waiting to push");
                    this.fullStackCondition.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();

            if (checkStackFull()) {
                System.out.println(Thread.currentThread().getName() + " pushing");
                System.out.println("The stack is full cannot push");
                System.out.println("Current Stack : " + stack.toString());
            } else {
                stack.push(writeElement);
                System.out.println(Thread.currentThread().getName() + " pushing");
                System.out.println("Value Write In : " + writeElement);
                System.out.println("Current Stack : " + stack.toString());
                this.emptyStackCondition.signalAll();
            }
        } finally {
            this.lock.unlock();
            System.out.println("Unlock " + Thread.currentThread().getName() + " from pushing");
        }
    }

    public void peek() {
        try {
            this.lock.lock();
            if (checkStackEmpty()) {
                try {
                    System.out.println();
                    System.out.println(Thread.currentThread().getName() + " peeking");
                    System.out.println("The stack is empty waiting to peek");
                    System.out.println("Current Stack : " + stack.toString());
                    this.emptyStackCondition.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();

            //check if srack is empty
            if (checkStackEmpty()) {
                System.out.println(Thread.currentThread().getName() + " peeking");
                System.out.println("The stack is empty cannot peek");
                System.out.println("Current Stack : " + stack.toString());
            } else {
                System.out.println(Thread.currentThread().getName() + " peeking");
                System.out.println("Peek Value : " + stack.peek());
                System.out.println("Current Stack : " + stack.toString());
            }
        } finally {
            this.lock.unlock();
            System.out.println("Unlock " + Thread.currentThread().getName() + " from peeking");
        }
    }

    private boolean checkStackEmpty() {
        return this.stack.isEmpty();
    }

    private boolean checkStackFull() {
        if (this.stack.size() >= this.stackSize) {
            return true;
        }
        return false;
    }

}
