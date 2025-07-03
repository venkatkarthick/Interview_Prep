package LLD.ConceptAndCoding.D_FactoryPattern.AbstractFactory.Products;

public class BMW implements Vehicle{
    @Override
    public String name() {
        return "BMW";
    }

    @Override
    public String toString() {
        return "BMW";
    }
}
