package LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code.Product;

import java.util.Date;

public class Vehicle {
    //vehicle Details to uniquely identify one
    int vehicleID;
    int vehicleNumber;
    VehicleType vehicleType;
    String companyName;
    String modelName;

    //vehicle's technical info
    int kmDriven;
    Date manufacturingDate;
    int average;
    int cc;
    int noOfSeats;

    //imp* - cost related details
    int dailyRentalCost;
    int hourlyRentalCost;
    Status status;

    public Vehicle(int vehicleID, int dailyRentalCost, VehicleType vehicleType) {
        this.vehicleID = vehicleID;
        this.dailyRentalCost = dailyRentalCost;
        this.vehicleType=vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID=" + vehicleID +
                ", dailyRentalCost=" + dailyRentalCost +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
