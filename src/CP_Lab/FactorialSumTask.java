package CP_Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FactorialSumTask extends RecursiveTask<Long> {

    private static int THRESHOLD = 5;
    private List<Long> integerList;

    private FactorialSumTask(List<Long> integerList){
        this.integerList = integerList;
    }

    @Override
    protected Long compute() {
        if (integerList.size() <= THRESHOLD){
            return sumFactorials();
        }else{
            int middle = integerList.size() / 2;
            FactorialSumTask subTask1 = new FactorialSumTask(integerList.subList(0, middle));
            FactorialSumTask subTask2 = new FactorialSumTask(integerList.subList(middle, integerList.size()));
            List<FactorialSumTask> subTasks = new ArrayList<FactorialSumTask>();
            subTasks.add(subTask1);
            subTasks.add(subTask2);
            ForkJoinTask.invokeAll(subTasks);
            Long sum = subTask1.join() + subTask2.join();
            return sum;
        }
    }

    private Long sumFactorials() {
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
        return sum;
    }

    public static void main(String[] args){
        List<Long> list = new ArrayList<>();
        final int RANGE = 20;
        for(int i=1; i<=RANGE; i++){
            list.add(new Long(i));
        }
        Long sum = ForkJoinPool.commonPool().invoke(new FactorialSumTask(list));
        System.out.println("Sum of the factorials for numbers 1 to 20 = " + sum);
    }

}
