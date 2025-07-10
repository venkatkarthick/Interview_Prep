package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow.Code;

import java.util.List;
import java.util.Objects;

public class Theatre {
    int theatreId;
    City city;
    List<Screen> screens;
    List<Show> shows;

    public Theatre(int theatreId, City city, List<Screen> screens) {
        this.theatreId = theatreId;
        this.city = city;
        this.screens = screens;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    @Override
    public int hashCode() {
        return Objects.hash(theatreId);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || this.getClass()!=obj.getClass()) return false;
        Movie curObj=(Movie) obj;
        return curObj.movieId==this.theatreId;
    }
}
