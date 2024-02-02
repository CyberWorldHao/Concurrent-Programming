package CP_Lab5.q1;

import java.util.Random;

public class WriteStack implements Runnable {

    private StackAccess stackAccess;

    public WriteStack(StackAccess stackAccess) {
        this.stackAccess = stackAccess;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i=1; i<=4; i++){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stackAccess.push(random.nextInt(100));
        }

    }
}
