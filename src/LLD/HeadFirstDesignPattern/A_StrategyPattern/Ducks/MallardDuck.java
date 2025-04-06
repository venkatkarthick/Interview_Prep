package LLD.HeadFirstDesignPattern.A_StrategyPattern.Ducks;

import LLD.HeadFirstDesignPattern.A_StrategyPattern.FlyBehaviour.FlyWithWings;
import LLD.HeadFirstDesignPattern.A_StrategyPattern.QuackBehaviour.Quack;

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
