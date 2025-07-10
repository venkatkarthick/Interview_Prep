package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow.Code;

import java.util.*;

public class MovieController {

    Map<City, List<Movie>> cityMovieMap;
    Set<Movie> allMovies;

    MovieController() {
        allMovies=new HashSet<>();
        cityMovieMap=new HashMap<>();
    }

    public void addMovie(Movie movie, City city) {
        allMovies.add(movie);
        if(!cityMovieMap.containsKey(city)) cityMovieMap.put(city, new ArrayList<>(List.of(movie)));
        else cityMovieMap.get(city).add(movie);
    }

    public Movie getMovieByName(String name) {
        for(Movie movie : allMovies) {
            if(movie.movieName.equals(name)) return movie;
        }
        return null;
    }

    public List<Movie> getMovieByCity(City userCity) {
        return cityMovieMap.get(userCity);
    }
}
