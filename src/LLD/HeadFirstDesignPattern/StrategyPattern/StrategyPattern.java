package LLD.HeadFirstDesignPattern.StrategyPattern;

import LLD.HeadFirstDesignPattern.StrategyPattern.Ducks.Duck;
import LLD.HeadFirstDesignPattern.StrategyPattern.Ducks.MallardDuck;
import LLD.HeadFirstDesignPattern.StrategyPattern.Ducks.ModelDuck;
import LLD.HeadFirstDesignPattern.StrategyPattern.FlyBehaviour.FlyRocketPowered;

public class StrategyPattern {

    //It defines a family of algorithms, encapsulates each one and make them interchangeable.
    //Startegy lets the alforithm vary independently from clients that uses it.

    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performQuack();

        Duck modelDuck = new ModelDuck();
        modelDuck.performFly();
        modelDuck.setFlyBehaviour(new FlyRocketPowered());
        modelDuck.performFly();
    }
}