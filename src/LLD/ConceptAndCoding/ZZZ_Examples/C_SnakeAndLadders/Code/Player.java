package LLD.ConceptAndCoding.ZZZ_Examples.C_SnakeAndLadders.Code;

public class Player {
    int playerId;
    int currentPos;

    Player(int playerId, int currentPos) {
        this.playerId=playerId;
        this.currentPos=currentPos;
    }

    @Override
    public String toString() {
        return "Player" + playerId + " is at position " + currentPos ;
    }
}
