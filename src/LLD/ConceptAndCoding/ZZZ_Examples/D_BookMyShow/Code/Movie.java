package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow.Code;

import java.util.Objects;

public class Movie {

    int movieId;
    String movieName;
    int movieDurationInMinutes;

    public Movie(int movieId, String movieName, int movieDurationInMinutes) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDurationInMinutes = movieDurationInMinutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || this.getClass()!=obj.getClass()) return false;
        Movie curObj=(Movie) obj;
        return curObj.movieId==this.movieId &&
                curObj.movieName.equals(this.movieName);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieDurationInMinutes=" + movieDurationInMinutes +
                '}';
    }
}
