package LLD.ConceptAndCoding.ZZZ_Examples.C_SnakeAndLadders.Code;

public class Cell {
    Jump jump;

    public Jump getJump() {
        return jump;
    }

    public void setJump(Jump jump) {
        this.jump = jump;
    }

    @Override
    public String toString() {
        if(jump==null) return "-";
        return "{"+jump+"}";
    }
}
