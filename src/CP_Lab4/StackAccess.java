package CP_Lab4;

import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class StackAccess {

    private Stack<Integer> stack;
    private Condition emptyStackCondition;
    private Condition fullStackCondition;
    private Lock lock;

    private final int STACK_SIZE;

    public StackAccess(int stackSize){
        this.stack = new Stack<Integer>();
        this.lock = new ReentrantLock();
        this.emptyStackCondition = this.lock.newCondition();
        this.fullStackCondition = this.lock.newCondition();
        this.STACK_SIZE = stackSize;
    }


    public void pop(){
        try{
            this.lock.lock();
            if (checkStackEmpty()){
                try {
                    System.out.println();
                    System.out.println(Thread.currentThread().getName() + " - Stack Empty. Waiting to pop...");
                    System.out.println(Thread.currentThread().getName() + " - Current Stack : " + stack.toString());
                    this.emptyStackCondition.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();

            //check again the stack if it is empty
            if (checkStackEmpty()){
                System.out.println(Thread.currentThread().getName() + " - Stack Empty. Cannot  Pop...");
                System.out.println(Thread.currentThread().getName() + " - Current Stack : " + stack.toString());
            }else{
                System.out.println(Thread.currentThread().getName() + " - Value Pop Out : " + stack.pop());
                System.out.println(Thread.currentThread().getName() + " - Current Stack : " + stack.toString());
                this.fullStackCondition.signalAll();
            }
        }finally {
            this.lock.unlock();
        }
    }

    public void push(int writeElement){
        try{
            this.lock.lock();
            if (checkStackFull()){
                try {
                    System.out.println();
                    System.out.println("Stack Full. Waiting to push....");
                    this.fullStackCondition.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();

            if(checkStackFull()){
                System.out.println("Stack Full. Cannot Push...");
                System.out.println(Thread.currentThread().getName() + " - Current Stack : " + stack.toString());
            }else{
                stack.push(writeElement);
                System.out.println(Thread.currentThread().getName() + " - Value Write In : " + writeElement);
                System.out.println(Thread.currentThread().getName() + " - Current Stack : " + stack.toString());
                this.emptyStackCondition.signalAll();
            }
        }finally {
            this.lock.unlock();
        }
    }

    public void peek(){
        try{
            this.lock.lock();
            if(checkStackEmpty()){
                try {
                    System.out.println();
                    System.out.println(Thread.currentThread().getName() + " - Stack Empty. Waiting to peek ...");
                    System.out.println(Thread.currentThread().getName() + " - Current Stack : " + stack.toString());
                    this.emptyStackCondition.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();

            //check if srack is empty
            if(checkStackEmpty()){
                System.out.println(Thread.currentThread().getName() + " - Stack Empty. Cannot Peek...");
                System.out.println(Thread.currentThread().getName() + " - Current Stack : " + stack.toString());
            }else{
                System.out.println(Thread.currentThread().getName() + " - Peek Value : " + stack.peek());
                System.out.println(Thread.currentThread().getName() + " - Current Stack : " + stack.toString());
            }
        }finally {
            this.lock.unlock();
        }
    }

    private boolean checkStackEmpty(){
        return this.stack.isEmpty();
    }

    private boolean checkStackFull(){
        if(this.stack.size()>=this.STACK_SIZE){
            return true;
        }
        return false;
    }


}
