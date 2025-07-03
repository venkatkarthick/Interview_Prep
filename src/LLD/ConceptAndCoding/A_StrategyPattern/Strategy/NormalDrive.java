package LLD.ConceptAndCoding.A_StrategyPattern.Strategy;

public class NormalDrive implements DriveStrategy{

    @Override
    public void drive() {
        System.out.println("Normal Drive...");
    }
}
