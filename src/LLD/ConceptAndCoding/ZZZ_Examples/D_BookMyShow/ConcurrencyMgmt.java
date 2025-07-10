package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow;

import java.util.HashMap;
import java.util.Map;

public class ConcurrencyMgmt {

    public Map<String, Seat> seatMap=new HashMap<>();
    public Map<String, Long> timerMap=new HashMap<>();
    private static final long LOCK_EXPIRY_MILLIS = 5000;

    public class Seat {
        String seatNumber;
        int version;
        Seat(String seatNumber, int version) {
            this.seatNumber=seatNumber;
            this.version=version;
        }
        public Seat copy() {
            return new ConcurrencyMgmt.Seat(this.seatNumber, this.version);
        }
    }

    public Seat readSeat(String seat) {
        if(seatMap.containsKey(seat)) {
            return seatMap.get(seat).copy();
        }
        return null;
    }

    private void updateUserSeat(String user, Seat userSeat) throws InterruptedException {
        //System.out.println("Out: "+user+" : "+userSeat.version);
        synchronized (ConcurrencyMgmt.class) {
            //System.out.println(user+" : "+userSeat.version);
            if(seatMap.get(userSeat.seatNumber).version!=userSeat.version) {
                System.out.println("Seat is blocked. Try again later!");
            } else {
                String seat=userSeat.seatNumber;
                timerMap.put(seat, System.currentTimeMillis());
                seatMap.get(userSeat.seatNumber).version++;
                Thread.sleep(100);
                timerMap.remove(seat);
                System.out.println(user + " booked "+userSeat.seatNumber);
            }
        }
    }

    void bookSeat(String user, String seat) {
        Seat userSeat = readSeat(seat);
        if(userSeat==null) {
            System.out.println("Seat ID is wrong!");
            return ;
        }
        System.out.println(user + " has version - V"+ userSeat.version+ " of the seat");

        try {
            updateUserSeat(user, userSeat);
        } catch (InterruptedException e) {
            System.out.println("Error : "+e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrencyMgmt cmt=new ConcurrencyMgmt();
        cmt.seatMap.put("S1", cmt.new Seat("S1", 1));

        Thread user1 = new Thread(() -> cmt.bookSeat("User1", "S1"));
        Thread user2 = new Thread(() -> cmt.bookSeat("User2", "S1"));

        user1.start();
        //Thread.sleep(50);
        user2.start();
    }

}
