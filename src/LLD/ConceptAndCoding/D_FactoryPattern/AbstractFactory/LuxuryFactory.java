package LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory;

import LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory.Products.BMW;
import LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory.Products.Vehicle;

public class LuxuryFactory implements VehicleFactory {

    @Override
    public Vehicle getVehicle(String name) {
        return switch (name) {
            case "BMW" -> new BMW();
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "LuxuryFactory";
    }
}
