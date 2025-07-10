package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow;

import java.util.HashMap;
import java.util.Map;

public class Concurrency {

    public Map<String, Seat> seatMap = new HashMap<>();
    public Map<String, Long> timerMap = new HashMap<>();
    private static final long LOCK_EXPIRY_MILLIS = 5000;

    public class Seat {
        String seatNumber;
        int version;

        Seat(String seatNumber, int version) {
            this.seatNumber = seatNumber;
            this.version = version;
        }

        public Seat copy() {
            return new Seat(this.seatNumber, this.version);
        }
    }

    // Read-only: return a copy to simulate snapshot
    public Seat readSeat(String seat) {
        if (seatMap.containsKey(seat)) {
            return seatMap.get(seat).copy();
        }
        return null;
    }

    // DO NOT use userSeat here — only use its version
    private void updateUserSeat(String user, String seatId, int userReadVersion) throws InterruptedException {
        synchronized (Concurrency.class) {
            Seat currentSeat = seatMap.get(seatId); // Always fresh

            if (currentSeat.version != userReadVersion) {
                System.out.println("Seat is blocked. Try again later!");
                return;
            }

            // Proceed with booking
            timerMap.put(seatId, System.currentTimeMillis());
            Thread.sleep(100); // Simulate payment
            currentSeat.version++; // Actual update
            System.out.println(user + " booked " + seatId);
            timerMap.remove(seatId);
        }
    }

    private void bookSeat(String user, String seatId) {
        // Simulate read before booking (stale snapshot)
        Seat snapshot = readSeat(seatId);
        if (snapshot == null) {
            System.out.println("Seat ID is wrong!");
            return;
        }

        System.out.println(user + " has version - V" + snapshot.version + " of the seat");

        try {
            updateUserSeat(user, seatId, snapshot.version);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Concurrency cmt = new Concurrency();
        cmt.seatMap.put("S1", cmt.new Seat("S1", 1));

        Thread user1 = new Thread(() -> cmt.bookSeat("User1", "S1"));
        Thread user2 = new Thread(() -> cmt.bookSeat("User2", "S1"));

        user1.start();
        Thread.sleep(50); // Let User1 get in first
        user2.start();
    }
}
