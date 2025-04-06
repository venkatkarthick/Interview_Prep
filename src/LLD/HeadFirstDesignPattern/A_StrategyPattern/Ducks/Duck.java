package LLD.HeadFirstDesignPattern.A_StrategyPattern.Ducks;

import LLD.HeadFirstDesignPattern.A_StrategyPattern.FlyBehaviour.FlyBehaviour;
import LLD.HeadFirstDesignPattern.A_StrategyPattern.QuackBehaviour.QuackBehaviour;

public abstract class Duck {
    FlyBehaviour flyBehaviour;
    QuackBehaviour quackBehaviour;
    public abstract void display();
    public void performFly(){
        flyBehaviour.fly();
    }
    public void performQuack(){
        quackBehaviour.quack();
    }
    public void swim(){
        System.out.println("All ducks float, even decoys!");
    }
    public void setFlyBehaviour(FlyBehaviour fb){
        flyBehaviour = fb;
    }
    public void setQuackBehaviour(QuackBehaviour qb){
        quackBehaviour = qb;
    }
}
