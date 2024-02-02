package CP_Lab6.Q2;

public class Timer {

    private long startTime;
    private long endTime;

    public Timer(){

    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void start(){
        this.startTime = System.currentTimeMillis();
    }

    public void finish(){
        this.endTime = System.currentTimeMillis();
    }

}
