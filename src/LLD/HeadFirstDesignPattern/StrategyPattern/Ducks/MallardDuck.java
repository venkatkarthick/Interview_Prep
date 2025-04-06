package LLD.HeadFirstDesignPattern.StrategyPattern.Ducks;

import LLD.HeadFirstDesignPattern.StrategyPattern.FlyBehaviour.FlyWithWings;
import LLD.HeadFirstDesignPattern.StrategyPattern.QuackBehaviour.Quack;

public class MallardDuck extends Duck{
    public MallardDuck() {
        flyBehaviour = new FlyWithWings();
        quackBehaviour = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I am a real Mallard duck");
    }
}
