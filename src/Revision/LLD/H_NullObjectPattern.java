package Revision.LLD;

public class H_NullObjectPattern {

    //Products
    interface Vehicle{
        public int getTankCapacity();
        public int getMileage();
    }
    static class Bike implements Vehicle{
        @Override
        public int getTankCapacity() {
            return 10;
        }
        @Override
        public int getMileage() {
            return 50;
        }
    }
    static class Car implements Vehicle{
        @Override
        public int getTankCapacity() {
            return 20;
        }
        @Override
        public int getMileage() {
            return 15;
        }
    }
    static class NullVehicle implements Vehicle{
        @Override
        public int getTankCapacity() {
            return 0;
        }
        @Override
        public int getMileage() {
            return 0;
        }
    }

    //Factory
    static class VehicleFactory{
        public Vehicle getVehicle(String name) {
            switch (name) {
                case "bike" : return new Bike();
                case "car" : return new Car();
                default: return new NullVehicle();
            }
        }
    }
    public static void main(String[] args) {
        VehicleFactory vehicleFactory=new VehicleFactory();

        Vehicle bike = vehicleFactory.getVehicle("bike");
        Vehicle scooter = vehicleFactory.getVehicle("scooter");
        System.out.println("Bike's Tank Capacity : " + bike.getTankCapacity());
        System.out.println("Scooter's Tank Capacity : " + scooter.getTankCapacity());
    }
}
