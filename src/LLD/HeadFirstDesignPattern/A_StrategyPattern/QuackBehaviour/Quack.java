package LLD.HeadFirstDesignPattern.A_StrategyPattern.QuackBehaviour;

public class Quack implements QuackBehaviour {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
