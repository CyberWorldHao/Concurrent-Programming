package CP_lab3;

public class Guest implements Runnable {

    private Room room;
    private String guestName;

    public Guest(Room room, String guestName) {
        this.room = room;
        this.guestName = guestName;
    }

    @Override
    public void run() {
        room.guestEnterRoomControl(guestName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        room.guestExitRoom(this.guestName);
    }
}
