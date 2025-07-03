package LLD.ConceptAndCoding.A_StrategyPattern;

import LLD.ConceptAndCoding.A_StrategyPattern.Strategy.NormalDrive;

public class PassengerVehicle extends Vehicle{
    PassengerVehicle() {
        super(new NormalDrive());
    }
}
