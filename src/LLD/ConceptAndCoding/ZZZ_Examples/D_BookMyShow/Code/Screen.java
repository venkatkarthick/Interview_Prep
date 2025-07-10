package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow.Code;

import java.util.List;

public class Screen {
    int screenId;
    List<Seat> seats;

    public Screen(int screenId, List<Seat> seats) {
        this.screenId=screenId;
        this.seats=seats;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "screenId=" + screenId +
                ", seats=" + seats +
                '}';
    }
}
