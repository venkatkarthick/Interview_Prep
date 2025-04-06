package LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Decorators;

import LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Components.Beverage;

public class Whip extends CondimentDecorator {
    public Whip(Beverage beverage) {
        this.beverage=beverage;
    }
    @Override
    public double cost() {
        return .10 + beverage.cost();
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }
}
