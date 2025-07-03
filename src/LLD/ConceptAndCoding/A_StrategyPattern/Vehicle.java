package LLD.ConceptAndCoding.A_StrategyPattern;

import LLD.ConceptAndCoding.A_StrategyPattern.Strategy.DriveStrategy;

public class Vehicle {
    DriveStrategy obj;
    Vehicle(DriveStrategy obj) {
        this.obj=obj;
    }
    public void drive() {
        obj.drive();
    }
}
