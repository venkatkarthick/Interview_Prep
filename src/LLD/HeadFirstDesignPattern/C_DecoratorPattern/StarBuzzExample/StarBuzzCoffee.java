package LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample;

import LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Components.Beverage;
import LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Components.DarkRoast;
import LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Components.Espresso;
import LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Components.HouseBlend;
import LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Decorators.Mocha;
import LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Decorators.Soy;
import LLD.HeadFirstDesignPattern.C_DecoratorPattern.StarBuzzExample.Decorators.Whip;

public class StarBuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

        Beverage beverage3 = new HouseBlend();
        beverage3=new Whip(new Mocha(new Soy(beverage3)));
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());

        //Double Mocha Soy Latte with whip
        Beverage beverage4 = new HouseBlend();
        beverage4=new Whip(new Mocha(new Mocha(new Soy(beverage4))));
        System.out.println(beverage4.getDescription() + " $" + beverage4.cost());
    }
}
