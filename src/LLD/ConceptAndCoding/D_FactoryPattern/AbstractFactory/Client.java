package LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory;

import LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory.Products.Vehicle;

public class Client {
    public static void main(String[] args) {
        String carSearch="BMW";
        VehicleAbstractFactory vehicleAbstractFactory=new VehicleAbstractFactory();
        VehicleFactory vehicleFactory= vehicleAbstractFactory.getVehicleFactory(carSearch);
        Vehicle vehicle=vehicleFactory.getVehicle(carSearch);
        System.out.println("Vehicle name is : "+vehicle.name());
    }
}
