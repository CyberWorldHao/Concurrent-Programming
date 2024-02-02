package CP_Lab5.q1;

public class PeekStack implements Runnable {

    private StackAccess stackAccess;

    public PeekStack(StackAccess stackAccess) {
        this.stackAccess = stackAccess;
    }

    @Override
    public void run() {
        //lock the operation to read
        for (int i=1; i<=4; i++){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stackAccess.peek();
        }
    }
}
