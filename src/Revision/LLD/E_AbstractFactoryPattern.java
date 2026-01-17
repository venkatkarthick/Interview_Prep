package Revision.LLD;

import java.util.List;

public class E_AbstractFactoryPattern {

    //It is factory of factories where each sub factory is itself a simple factory.

    //Products
    interface Vehicle {
        public String getName();
    }
    static class BMW implements Vehicle{
        @Override
        public String getName() {
            return "BMW";
        }
    }
    static class Hyundai implements Vehicle{
        @Override
        public String getName() {
            return "Hyundai";
        }
    }

    //Factories
    interface VehicleFactory{
        public Vehicle getVehicle(String name);
    }
    static class LuxuryVehicle implements VehicleFactory {
        public Vehicle getVehicle(String name) {
            switch (name) {
                case "BMW" : return new BMW();
                default: return null;
            }
        }
    }
    static class OrdinaryVehicle implements VehicleFactory {
        public Vehicle getVehicle(String name) {
            switch (name) {
                case "HUN" : return new Hyundai();
                default: return null;
            }
        }
    }

    static class VehicleAbstractFactory {
        public VehicleFactory getVehicleFactory(String name) {
            if(List.of("BMW").contains(name)) return new LuxuryVehicle();
            else if(List.of("HUN").contains(name)) return new OrdinaryVehicle();
            else return null;
        }
    }

    public static void main(String[] args) {
        String searchVehicle = "HUN";
        VehicleAbstractFactory vehicleAbstractFactory = new VehicleAbstractFactory();
        VehicleFactory vehicleFactory = vehicleAbstractFactory.getVehicleFactory(searchVehicle);
        Vehicle vehicle = vehicleFactory.getVehicle(searchVehicle);
        System.out.println("Vehicle : " + vehicle.getName());
    }
}
