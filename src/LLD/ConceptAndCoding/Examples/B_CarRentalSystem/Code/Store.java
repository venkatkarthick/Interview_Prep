package LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code;

import LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code.Product.Vehicle;
import LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code.Product.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Store {

    int storeId;
    Location location;
    VehicleInventoryManagement vehicleInventoryManagement;
    List<Reservation> reservationList=new ArrayList<>();

    public Store(int storeId, List<Vehicle> vehicles, Location location) {
        this.storeId = storeId;
        this.location = location;
        setVehicles(vehicles);
    }

    public Reservation createReservation(Vehicle vehicle, User user) {
        Reservation reservation=new Reservation(user, vehicle);
        reservationList.add(reservation);
        return reservation;
    }

    public boolean completeReservation(Reservation reservation) {
        //take reservation from the list and call complete reservation
        reservation.completeRide(reservation);
        return true;
    }

    //add or update vehicles using inventory Management

    public List<Vehicle> getVehicles(VehicleType vehicleType) {

        //vehicleType is the filter type. Based on the filter get the vehicles
        return vehicleInventoryManagement.getVehicles();
    }
    public void setVehicles(List<Vehicle> vehicles) {
        vehicleInventoryManagement = new VehicleInventoryManagement(vehicles);
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", location=" + location +
                ", vehicleInventoryManagement=" + vehicleInventoryManagement +
                ", reservationList=" + reservationList +
                '}';
    }
}
