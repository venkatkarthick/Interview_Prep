package LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code;

import LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code.Product.Vehicle;

import java.util.List;

public class VehicleInventoryManagement {

    List<Vehicle> vehicles;

    public VehicleInventoryManagement(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    //getters and setters

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "VehicleInventoryManagement{" +
                "vehicles=" + vehicles +
                '}';
    }
}
