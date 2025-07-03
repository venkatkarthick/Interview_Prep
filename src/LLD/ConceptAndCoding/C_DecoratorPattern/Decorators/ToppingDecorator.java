package LLD.ConceptAndCoding.C_DecoratorPattern.Decorators;

import LLD.ConceptAndCoding.C_DecoratorPattern.Pizza;

public abstract class ToppingDecorator extends Pizza{
    Pizza pizza;
    ToppingDecorator(Pizza pizza) {
        this.pizza=pizza;
    }
}
