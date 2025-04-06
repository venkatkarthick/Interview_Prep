package LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Decorators;

import LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Components.Beverage;

public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;
    public abstract String getDescription();
}
