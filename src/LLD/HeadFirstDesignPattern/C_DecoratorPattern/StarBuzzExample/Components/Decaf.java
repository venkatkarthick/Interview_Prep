package LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Components;

public class Decaf extends Beverage {
    public Decaf() {
        description = "Decaf Coffee";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
