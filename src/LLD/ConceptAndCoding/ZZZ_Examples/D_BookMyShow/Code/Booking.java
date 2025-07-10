package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow.Code;

import java.util.List;

public class Booking {
    Show show;
    List<Seat> seats;
    Payment payment;

    public Booking(Show show, List<Seat> seats) {
        this.show = show;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "show=" + show +
                ", seats=" + seats +
                ", payment=" + payment +
                '}';
    }
}
