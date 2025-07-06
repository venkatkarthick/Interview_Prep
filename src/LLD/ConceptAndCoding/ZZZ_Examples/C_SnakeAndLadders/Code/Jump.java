package LLD.ConceptAndCoding.ZZZ_Examples.C_SnakeAndLadders.Code;

public class Jump {
    int startPos;
    int endPos;

    public Jump(int startPos, int endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public String toString() {
        return "[ "+ startPos +", " + endPos + "]";
    }
}
