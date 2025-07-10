package LLD.ConceptAndCoding.H_StateDesignPattern.Code;

public enum Coin {
    ONE(1), TWO(5), FIVE(5), TEN(10);

    public final int value;

    Coin(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return " "+value+" ";
    }
}
