package LLD.HeadFirstDesignPattern.A_StrategyPattern.FlyBehaviour;

public class FlyNoWay implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("I cannot fly");
    }
}
