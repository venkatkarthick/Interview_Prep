package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow.Code;

import java.util.*;

public class TheatreController {

    Map<City, List<Theatre>> cityTheatreMap;
    Set<Theatre> allTheatres;

    TheatreController() {
        cityTheatreMap=new HashMap<>();
        allTheatres=new HashSet<>();
    }

    public void addTheatre(Theatre theatre) {
        allTheatres.add(theatre);
        if(!cityTheatreMap.containsKey(theatre.city)) cityTheatreMap.put(theatre.city, new ArrayList<>(List.of(theatre)));
        else cityTheatreMap.get(theatre.city).add(theatre);
    }

    public Map<Theatre, List<Show>> getAllShows(Movie interestedMovie, City userCity) {
        Map<Theatre, List<Show>> allMovieShows = new HashMap<>();
        List<Theatre> theatresInCity = cityTheatreMap.get(userCity);
        for(Theatre theatre : theatresInCity) {
            for(Show show : theatre.shows) {
                if(show.movie.equals(interestedMovie)) {
                    if(!allMovieShows.containsKey(theatre)) allMovieShows.put(theatre, new ArrayList<>(List.of(show)));
                    else allMovieShows.get(theatre).add(show);
                }
            }
        }
        return allMovieShows;
    }

}
