package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow.Code;

import java.util.ArrayList;
import java.util.List;

public class Show {
    int showId;
    Movie movie;
    Screen screen;
    int showStartTime;
    List<Integer> bookedSeatIDs;

    public Show(int showId, Movie movie, Screen screen, int showStartTime) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.showStartTime = showStartTime;
        bookedSeatIDs=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Show{" +
                "showId=" + showId +
                ", movie=" + movie +
                ", showStartTime=" + showStartTime +
                ", bookedSeatIDs=" + bookedSeatIDs +
                '}';
    }
}
