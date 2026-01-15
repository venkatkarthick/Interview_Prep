package Revision.LLD;

import LLD.ConceptAndCoding.C_DecoratorPattern.Margharita;

public class C_DecoratorPattern {

    static abstract class Pizza {
        public int cost() {
            return 100;
        }
    }
    static class Margharita extends Pizza{
        public int cost() {
            return 120;
        }
    }
    static class CheeseBurst extends Pizza{
        public int cost() {
            return 150;
        }
    }

    //Topping Decorator!!!
    static abstract class Topping extends Pizza{
        Pizza pizza;
        Topping(Pizza pizza) {
            this.pizza=pizza;
        }
    }
    static class Mushroom extends Topping{
        Mushroom(Pizza pizza) {
            super(pizza);
        }
        public int cost() {
            return pizza.cost()+20;
        }
    }
    static class Chicken extends Topping{
        Chicken(Pizza pizza) {
            super(pizza);
        }
        public int cost() {
            return pizza.cost()+50;
        }
    }

    public static void main(String[] args) {
        Pizza mushroomMargharita = new Mushroom(new Margharita());
        Pizza chickenMushroomCheeseBurst = new Chicken(new Mushroom(new CheeseBurst()));

        System.out.println("Cost of Pizza 1 : " +mushroomMargharita.cost());
        System.out.println("Cost of Pizza 2 : " +chickenMushroomCheeseBurst.cost());
    }
}
