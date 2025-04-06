package LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Components;

public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
