package CP_Lab5.q1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        // write your code here
        StackAccess stackAccess = new StackAccess(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //There is only push/ pop/ peek task
//        executorService.execute(new ReadStack(stackAccess));
//
//        executorService.execute(new WriteStack(stackAccess));
//
//        executorService.execute(new PeekStack(stackAccess));
//
//        //There are two types of task:
//        //push & pop
//        executorService.execute(new WriteStack(stackAccess));
//        executorService.execute(new ReadStack(stackAccess));
//
//        //push & peek
//        executorService.execute(new WriteStack(stackAccess));
//        executorService.execute(new PeekStack(stackAccess));
//
//        //pop & peek
//        executorService.execute(new ReadStack(stackAccess));
//        executorService.execute(new PeekStack(stackAccess));
//
//        //there are three types of task:
//        executorService.execute(new ReadStack(stackAccess));
//        executorService.execute(new WriteStack(stackAccess));
//        executorService.execute(new PeekStack(stackAccess));
//
//        //Produce more data than it is consumed
//        executorService.execute(new WriteStack(stackAccess));
//        executorService.execute(new WriteStack(stackAccess));
//        executorService.execute(new ReadStack(stackAccess));
//
//        //Consume more data then it is produced
//        executorService.execute(new WriteStack(stackAccess));
//        executorService.execute(new ReadStack(stackAccess));
//        executorService.execute(new ReadStack(stackAccess));

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
