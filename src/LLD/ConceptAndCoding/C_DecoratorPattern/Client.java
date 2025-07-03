package LLD.ConceptAndCoding.C_DecoratorPattern;

import LLD.ConceptAndCoding.C_DecoratorPattern.Decorators.ExtraCheese;
import LLD.ConceptAndCoding.C_DecoratorPattern.Decorators.Mushroom;

public class Client {
    public static void main(String[] args) {
        Pizza margharitaCheeseMushroom = new Mushroom(new ExtraCheese(new Margharita()));
        System.out.println("Cost of MargharitaCheeseMushroom is : "+margharitaCheeseMushroom.cost());

        Pizza vegDelightDoubleCheese = new ExtraCheese(new ExtraCheese(new VegDelight()));
        System.out.println("Cost of VegDelightDoubleCheese is :  "+vegDelightDoubleCheese.cost());
    }
}
