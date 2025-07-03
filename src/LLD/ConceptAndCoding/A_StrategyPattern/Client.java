package LLD.ConceptAndCoding.A_StrategyPattern;

public class Client {
    public static void main(String[] args) {
        Vehicle vehicle=new SportyVehicle();
        vehicle.drive();

        vehicle=new PassengerVehicle();
        vehicle.drive();

    }
}
