package CP_Lab4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        StackAccess stackAccess = new StackAccess(6);
        ExecutorService executorReadService = Executors.newFixedThreadPool(3);
        ExecutorService executorWriteService = Executors.newFixedThreadPool(2);
        ExecutorService executorPeekService = Executors.newFixedThreadPool(1);

//        Three type of task (push, pop, peek)
//        executorReadService.execute(new ReadStack(stackAccess));
//        executorWriteService.execute(new WriteStack(stackAccess));
//        executorPeekService.execute(new PeekStack(stackAccess));
        
//        There are two types of task: push & pop, push & peek, pop & peek.
//        executorReadService.execute(new ReadStack(stackAccess));
//        executorWriteService.execute(new WriteStack(stackAccess));

//        Produce more data than it is consumed
//        executorWriteService.execute(new WriteStack(stackAccess));
//        executorPeekService.execute(new PeekStack(stackAccess));

//        Consume more data then it is produced
//        executorWriteService.execute(new WriteStack(stackAccess));
//        executorReadService.execute(new ReadStack(stackAccess));
//        executorPeekService.execute(new PeekStack(stackAccess));
//        executorReadService.execute(new ReadStack(stackAccess));
//        executorPeekService.execute(new PeekStack(stackAccess));
//        executorReadService.execute(new ReadStack(stackAccess));
//        executorPeekService.execute(new PeekStack(stackAccess));
//        executorReadService.execute(new ReadStack(stackAccess));
//        executorPeekService.execute(new PeekStack(stackAccess));

        while (true) {
            if (executorReadService.isTerminated() && executorWriteService.isTerminated() && executorPeekService.isTerminated()) {
                System.out.println();
                System.out.println("Program End");
                break;
            }
        }

    }
}
