package LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Decorators;

import LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Components.Beverage;

public class Mocha extends CondimentDecorator {
    public Mocha(Beverage beverage) {
        this.beverage=beverage;
    }
    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }
}
