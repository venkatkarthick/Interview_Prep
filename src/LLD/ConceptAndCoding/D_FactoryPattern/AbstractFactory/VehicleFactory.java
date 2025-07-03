package LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory;

import LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory.Products.Vehicle;

public interface VehicleFactory {
    Vehicle getVehicle(String name);
}
