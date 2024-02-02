package CP_Lab.Q2;

import CP_Lab.FactorialSumTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class FactorialSumAction extends RecursiveAction {

    private static int THRESHOLD = 5;
    private List<Long> integerList;
    private static Long grandTotal = new Long(0);

    private FactorialSumAction(List<Long> integerList){
        this.integerList = integerList;
    }

    @Override
    protected void compute() {
        if (integerList.size() <= THRESHOLD){
            sumFactorials();
        }else{
            int middle = integerList.size() / 2;
            FactorialSumAction subTask1 = new FactorialSumAction(integerList.subList(0, middle));
            FactorialSumAction subTask2 = new FactorialSumAction(integerList.subList(middle, integerList.size()));
            List<FactorialSumAction> subTasks = new ArrayList<FactorialSumAction>();
            subTasks.add(subTask1);
            subTasks.add(subTask2);
            ForkJoinTask.invokeAll(subTasks);
        }
    }

    private void sumFactorials() {
        Long sum = new Long(0);
        int i = 0;
        while(i < integerList.size()) {
            Long factorial = new Long(1);
            Long number = new Long(integerList.get(i));
            for (int j=1; j<=number; j++){
                factorial *= j;
            }
            sum += factorial;
            i++;
            System.out.println("Thread: " + Thread.currentThread().getName() + ": number: " + number + ": factorial sum: " + factorial);
        }
        synchronized (this){
            grandTotal += sum;
        }
    }

    public static void main(String[] args){
        List<Long> list = new ArrayList<>();
        final int RANGE = 20;
        for(int i=1; i<=RANGE; i++){
            list.add(new Long(i));
        }
        ForkJoinPool.commonPool().invoke(new FactorialSumAction(list));
        System.out.println("Sum of the factorials for numbers 1 to 20 = " + grandTotal);
    }
}
