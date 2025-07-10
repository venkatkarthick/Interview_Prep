package LLD.ConceptAndCoding.G_NullObjectPattern.Code;

public class NullVehicle implements Vehicle{

    @Override
    public int getTankCapacity() {
        return -1;
    }

    @Override
    public int getSeatingCapacity() {
        return -1;
    }
}
