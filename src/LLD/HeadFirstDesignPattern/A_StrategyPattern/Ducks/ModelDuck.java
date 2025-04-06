package LLD.HeadFirstDesignPattern.A_StrategyPattern.Ducks;

import LLD.HeadFirstDesignPattern.A_StrategyPattern.FlyBehaviour.FlyNoWay;
import LLD.HeadFirstDesignPattern.A_StrategyPattern.QuackBehaviour.MuteQuack;

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
