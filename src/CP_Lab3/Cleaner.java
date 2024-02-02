package CP_lab3;

public class Cleaner implements Runnable{

    private Room room;
    private String cleanerName;

    public Cleaner(Room room, String cleanerName){
        this.room = room;
        this.cleanerName = cleanerName;
    }

    @Override
    public void run(){
        room.cleanerEnterRoomControl(this.cleanerName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        room.cleanerExitRoom(this.cleanerName);
    }

}
