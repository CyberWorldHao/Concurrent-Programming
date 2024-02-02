package CP_lab3;

import java.util.ArrayList;

public class Room {

    private ArrayList<String> guestArray = new ArrayList<>();
    private String cleanerName = "";

    public synchronized void guestExitRoom(String guestName) {
        guestArray.remove(guestName);
        System.out.println();
        System.out.println(Thread.currentThread().getName() + " : " + guestName + " leave the room.");
        notifyAll();
    }

    public synchronized void cleanerExitRoom(String cleanerName) {
        this.cleanerName = "";
        System.out.println();
        System.out.println(Thread.currentThread().getName() + " : " + cleanerName + " leave the room.");
        notifyAll();
    }

    public synchronized void guestEnterRoomControl(String guestName) {
        if (cleanerName == "") {
            if (guestArray.size() >= 6) {
                System.out.println();
                System.out.println(Thread.currentThread().getName() + " : The room can only allowed maximum of 6 people.");
                printRoom();
                //lock
                while (guestArray.size() >= 6) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                guestEnterRoomControl(guestName);

            } else {
                guestEnterRoom(guestName);
            }
        } else {
            System.out.println();
            System.out.println(Thread.currentThread().getName() + " : Please wait cleaner to clean the room.");
            printRoom();
            //lock
            while (cleanerName != "") {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            guestEnterRoomControl(guestName);

        }

    }

    public synchronized void cleanerEnterRoomControl(String cleanerName) {
        if (guestArray.size() == 0) {
            if (this.cleanerName == "") {
                cleanerEnterRoom(cleanerName);
            } else {
                System.out.println();
                System.out.println(Thread.currentThread().getName() + " : " + cleanerName + " are not allowed to clean the room. Only one cleaner is allowed.");
                printRoom();
                //lock
                while (this.cleanerName != "") {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                cleanerEnterRoomControl(cleanerName);
            }
        } else if (guestArray.size() > 0) {
            System.out.println();
            System.out.println(Thread.currentThread().getName() + " : " + cleanerName + " are not allowed to enter the room because there are guests inside the room.");
            printRoom();
            //lock
            while (guestArray.size() > 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cleanerEnterRoomControl(cleanerName);
        }
    }

    public void guestEnterRoom(String guestName) {
        System.out.println();
        System.out.println(Thread.currentThread().getName() + " : " + guestName + " enter the room.");
        guestArray.add(guestName);
        printRoom();
    }

    public void cleanerEnterRoom(String cleanerName) {
        System.out.println();
        System.out.println(Thread.currentThread().getName() + " : " + cleanerName + " enter the room.");
        this.cleanerName = cleanerName;
        printRoom();
    }

    public void printRoom() {
        System.out.println(Thread.currentThread().getName() + " : Number of guest in the room: " + this.guestArray.size() + " ( " + guestArray + " )");
        if (this.cleanerName != "") {
            System.out.println(Thread.currentThread().getName() + " : The room is cleaning by " + this.cleanerName);
        } else {
            System.out.println(Thread.currentThread().getName() + " : There is no cleaner in the room");
        }
    }

}
