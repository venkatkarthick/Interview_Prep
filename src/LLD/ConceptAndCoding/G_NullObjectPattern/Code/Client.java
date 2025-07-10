package LLD.ConceptAndCoding.G_NullObjectPattern.Code;


public class Client {
    public static void main(String[] args) {
        Vehicle vehicle= VehicleFactory.getVechicle("Bike");
        printVehicleDetails(vehicle);
        Vehicle vehicle1= VehicleFactory.getVechicle("Flight");
        printVehicleDetails(vehicle1);
    }

    private static void printVehicleDetails(Vehicle vehicle) {
        System.out.println("Seating Capacity : " + vehicle.getSeatingCapacity());
        System.out.println("Tank Capacity : " + vehicle.getTankCapacity());
    }
}
