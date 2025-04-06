package LLD.HeadFirstDesignPattern.StrategyPattern.Ducks;

import LLD.HeadFirstDesignPattern.StrategyPattern.FlyBehaviour.FlyNoWay;
import LLD.HeadFirstDesignPattern.StrategyPattern.QuackBehaviour.MuteQuack;

public class ModelDuck extends Duck{
    public ModelDuck() {
        flyBehaviour = new FlyNoWay();
        quackBehaviour = new MuteQuack();
    }
    @Override
    public void display() {
        System.out.println("I am a model duck");
    }
}
