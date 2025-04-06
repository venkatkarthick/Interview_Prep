package LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Components;

public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
