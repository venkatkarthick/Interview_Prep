package LLD.ConceptAndCoding.A_StrategyPattern.Strategy;

public class SpecialDrive implements DriveStrategy{

    @Override
    public void drive() {
        System.out.println("Special Drive...");
    }
}
