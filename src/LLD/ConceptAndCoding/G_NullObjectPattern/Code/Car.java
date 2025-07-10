package LLD.ConceptAndCoding.G_NullObjectPattern.Code;

public class Car implements Vehicle{
    @Override
    public int getTankCapacity() {
        return 25;
    }

    @Override
    public int getSeatingCapacity() {
        return 4;
    }
}
