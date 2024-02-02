package CP_lab3;

public class Q1 {

    public static void main(String args[]){
        Room room = new Room();
        Cleaner cleaner1 = new Cleaner(room, "cleaner1");
        Cleaner cleaner2 = new Cleaner(room, "cleaner2");
        Cleaner cleaner3 = new Cleaner(room, "cleaner3");
        Cleaner cleaner4 = new Cleaner(room, "cleaner4");
        Cleaner cleaner5 = new Cleaner(room, "cleaner5");
        Guest guest1 = new Guest(room, "guest1");
        Guest guest2 = new Guest(room, "guest2");
        Guest guest3 = new Guest(room, "guest3");
        Guest guest4 = new Guest(room, "guest4");
        Guest guest5 = new Guest(room, "guest5");
        Guest guest6 = new Guest(room, "guest6");
        Guest guest7 = new Guest(room, "guest7");
        Guest guest8 = new Guest(room, "guest8");
        Guest guest9 = new Guest(room, "guest9");
        Guest guest10 = new Guest(room, "guest10");

        Thread thread1 = new Thread(cleaner1);
        Thread thread2 = new Thread(cleaner2);
        Thread thread3 = new Thread(cleaner3);
        Thread thread4 = new Thread(cleaner4);
        Thread thread5 = new Thread(cleaner5);
        Thread thread6 = new Thread(guest1);
        Thread thread7 = new Thread(guest2);
        Thread thread8 = new Thread(guest3);
        Thread thread9 = new Thread(guest4);
        Thread thread10 = new Thread(guest5);
        Thread thread11 = new Thread(guest6);
        Thread thread12 = new Thread(guest7);
        Thread thread13 = new Thread(guest8);
        Thread thread14 = new Thread(guest9);
        Thread thread15 = new Thread(guest10);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        thread11.start();
        thread12.start();
        thread13.start();
        thread14.start();
        thread15.start();
    }
}
