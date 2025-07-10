package LLD.ConceptAndCoding.G_NullObjectPattern.Code;

public class VehicleFactory {

    public static Vehicle getVechicle(String vehicle) {
        if(vehicle.equals("Bike")) return new Bike();
        else if(vehicle.equals("Car")) return new Car();
        else return new NullVehicle();
    }
}
