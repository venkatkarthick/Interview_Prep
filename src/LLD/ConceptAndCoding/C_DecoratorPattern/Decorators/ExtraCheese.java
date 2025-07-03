package LLD.ConceptAndCoding.C_DecoratorPattern.Decorators;

import LLD.ConceptAndCoding.C_DecoratorPattern.Pizza;

public class ExtraCheese extends ToppingDecorator {
    public ExtraCheese(Pizza pizza) {
        super(pizza);
    }
    @Override
    public int cost() {
        return pizza.cost()+10;
    }
}
