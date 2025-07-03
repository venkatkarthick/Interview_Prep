package LLD.ConceptAndCoding.A_StrategyPattern;

import LLD.ConceptAndCoding.A_StrategyPattern.Strategy.SpecialDrive;

public class SportyVehicle extends Vehicle{
    SportyVehicle() {
        super(new SpecialDrive());
    }
}
