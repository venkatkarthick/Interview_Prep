package LLD.ConceptAndCoding.C_DecoratorPattern.Decorators;

import LLD.ConceptAndCoding.C_DecoratorPattern.Pizza;

public class Mushroom extends ToppingDecorator {
    public Mushroom(Pizza pizza) {
        super(pizza);
    }
    @Override
    public int cost() {
        return pizza.cost()+20;
    }
}