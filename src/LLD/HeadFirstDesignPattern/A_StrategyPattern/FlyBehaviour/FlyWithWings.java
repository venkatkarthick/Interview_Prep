package LLD.HeadFirstDesignPattern.A_StrategyPattern.FlyBehaviour;

public class FlyWithWings implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("I am flying");
    }
}
