package LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory;

import LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory.Products.BMW;
import LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory.Products.Hyundai;
import LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory.Products.Vehicle;

public class OrdinaryFactory implements VehicleFactory {

    @Override
    public Vehicle getVehicle(String name) {
        return switch (name) {
            case "Hyundai" -> new Hyundai();
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "ordinaryFactory";
    }
}