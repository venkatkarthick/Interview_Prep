package LLD.ConceptAndCoding.ZZZ_Examples.C_SnakeAndLadders.Code;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    int diceCount;

    Dice(int count) {
        this.diceCount=count;
    }

    public int rollDice() {
        return ThreadLocalRandom.current().nextInt(1, 6*diceCount);
    }

}
