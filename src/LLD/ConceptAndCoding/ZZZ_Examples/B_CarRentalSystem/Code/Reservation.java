package LLD.ConceptAndCoding.ZZZ_Examples.B_CarRentalSystem.Code;

import LLD.ConceptAndCoding.ZZZ_Examples.B_CarRentalSystem.Code.Product.Vehicle;

import java.util.Date;

public class Reservation {
    int reservationID;
    User user;
    Vehicle vehicle;

    Date bookingDate;
    Date bookedDateFrom;
    Date bookedDateTill;

    Location pickUpLocation;
    Location dropLocation;
    Location bookedLocation;

    BookingStatus bookingStatus;
    ReservationType reservationType;

    public Reservation(User user, Vehicle vehicle) {
        //generate new ID;
        reservationID=12211;
        this.user=user;
        this.vehicle=vehicle;
        reservationType= ReservationType.DAILY;
        bookingStatus= BookingStatus.SCHEDULED;
    }

    public void completeRide(Reservation reservation) {
        reservation.bookingStatus= BookingStatus.COMPLETED;
    }

    //complete reservation and modify reservation methods to be added here
    //CRUD

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationID=" + reservationID +
                ", user=" + user +
                ", vehicle=" + vehicle +
                ", bookingDate=" + bookingDate +
                ", bookedDateFrom=" + bookedDateFrom +
                ", bookedDateTill=" + bookedDateTill +
                ", pickUpLocation=" + pickUpLocation +
                ", dropLocation=" + dropLocation +
                ", bookedLocation=" + bookedLocation +
                ", bookingStatus=" + bookingStatus +
                ", reservationType=" + reservationType +
                '}';
    }
}
