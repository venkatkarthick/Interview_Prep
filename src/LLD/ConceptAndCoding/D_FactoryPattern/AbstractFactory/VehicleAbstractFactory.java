package LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory;

import java.util.Arrays;
import java.util.List;

public class VehicleAbstractFactory {
    public VehicleFactory getVehicleFactory(String name) {
        List<String> luxuryCars = List.of("BMW");
        List<String> ordinaryCars = List.of("Hyundai");
        if(luxuryCars.contains(name)) {
            return new LuxuryFactory();
        } else if(ordinaryCars.contains(name)) {
            return new OrdinaryFactory();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "VehicleAbstractFactory";
    }
}
