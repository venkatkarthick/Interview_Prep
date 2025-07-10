package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow.Code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {

    MovieController movieController;
    TheatreController theatreController;

    BookMyShow() {
        movieController=new MovieController();
        theatreController=new TheatreController();
    }

    public static void main(String[] args) {
        BookMyShow bookMyShow=new BookMyShow();
        bookMyShow.initialize();

        //user1's booking
        bookMyShow.createBooking(City.Bangalore, "Avengers");

        //user2's booking
        bookMyShow.createBooking(City.Bangalore, "Avengers");
    }

    private void createBooking(City userCity, String movieName) {

        //1. search movie by location
        List<Movie> userMovies = movieController.getMovieByCity(userCity);

        //2. Select the movie which you want to see (avengers)
        Movie interestedMovie=null;
        for (Movie movie : userMovies) {
            if(movie.movieName.equals(movieName)) {
                interestedMovie=movie;
            }
        }

        //3. Get all show of this movie in Banagalore
        Map<Theatre, List<Show>> showsTheatreWise = theatreController.getAllShows(interestedMovie, City.Bangalore);

        //4. Get the show user is interested in
        Theatre interestedTheatre = showsTheatreWise.keySet().iterator().next();
        Show interestedShow = showsTheatreWise.get(interestedTheatre).get(0);

        //5.Select the seat
        List<Integer> interestedSeatNumbers=List.of(11, 12);
        for(Integer interestedSeatNumber : interestedSeatNumbers) {
            if(interestedShow.bookedSeatIDs.contains(interestedSeatNumber)) {
                System.out.println(interestedSeatNumber + " is already booked. Try Again");
                return ;
            }
        }
        //Start Payment
        interestedShow.bookedSeatIDs.addAll(interestedSeatNumbers);
        List<Seat> selectedSeats=new ArrayList<>();
        for(Seat seat : interestedShow.screen.seats) {
           if(interestedSeatNumbers.contains(seat.id)) {
               selectedSeats.add(seat);
           }
        }
        Booking booking = new Booking(interestedShow, selectedSeats);
        System.out.println("Booked successfully!! Booking Details : " + booking);
    }

    private void initialize() {

        //Create Movies
        createMovies();

        //Create Theatre with Screens, seats and shows
        createTheatre();

    }

    private void createTheatre() {
        Movie avenger = movieController.getMovieByName("Avengers");
        Movie godfather = movieController.getMovieByName("GodFather 1");

        Theatre inox = new Theatre(1, City.Bangalore, createScreens(10, 20));
        List<Show> shows1=List.of(
                new Show(1, avenger, inox.screens.get(0), 10),
                new Show(2, godfather, inox.screens.get(0), 18)
        );
        inox.setShows(shows1);

        Theatre pvr = new Theatre(2, City.Chennai, createScreens(5, 10));
        List<Show> shows2=List.of(
                new Show(3, avenger, pvr.screens.get(0), 10),
                new Show(4, godfather, pvr.screens.get(0), 18)
        );
        pvr.setShows(shows2);
        theatreController.addTheatre(inox);
        theatreController.addTheatre(pvr);
    }

    private List<Screen> createScreens(int seatRow, int seatCol) {
        return List.of(new Screen(1, createSeats(seatRow, seatCol)));
    }

    private List<Seat> createSeats(int row, int col) {
        List<Seat> seats = new ArrayList<>();
        int seatIDGenerator=0;
        for(int i=0; i<row; i++) {
            for (int j = 0; j < col; j++) {
                if(i==0) seats.add(new Seat((seatIDGenerator++), i, SeatCategory.PLATINUM));
                else if(i==1) seats.add(new Seat((seatIDGenerator++), i, SeatCategory.GOLD));
                else seats.add(new Seat((seatIDGenerator++), i, SeatCategory.SILVER));
            }
        }
        return seats;
    }

    private void createMovies() {
        //create Movie1
        Movie avengers=new Movie(1, "Avengers", 180);
        Movie godFather=new Movie(2, "GodFather 1", 165);

        movieController.addMovie(avengers, City.Bangalore);
        movieController.addMovie(avengers, City.Chennai);
        movieController.addMovie(godFather, City.Bangalore);
        movieController.addMovie(godFather, City.Chennai);
        movieController.addMovie(avengers, City.Kolkata);
    }

}
