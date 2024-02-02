package CP_Lab5.q1;

public class ReadStack implements Runnable {

    private StackAccess stackAccess;

    public ReadStack(StackAccess stackAccess) {
        this.stackAccess = stackAccess;
    }

    @Override
    public void run() {
        for (int i=1; i<=4; i++){
            
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stackAccess.pop();
        }
    }
}
