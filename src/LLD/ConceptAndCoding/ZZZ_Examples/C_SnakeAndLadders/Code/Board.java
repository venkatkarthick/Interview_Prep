package LLD.ConceptAndCoding.ZZZ_Examples.C_SnakeAndLadders.Code;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    Cell[][] cells;

    Board(int boardSize, int noOfLadders, int noOfSnakes) {
        initializeCells(boardSize);
        addSnakesAndLadders(noOfLadders, noOfSnakes);
    }

    private void initializeCells(int boardSize) {
        cells=new Cell[boardSize][boardSize];
        for(int i=0; i<boardSize; i++) {
            for(int j=0; j<boardSize; j++) {
                cells[i][j]=new Cell();
            }
        }
    }

    private void addSnakesAndLadders(int noOfLadders, int noOfSnakes) {
        while(noOfSnakes!=0) {
            int snakeHead= ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int snakeTail= ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            if(snakeHead<=snakeTail) {
                continue;
            }
            getCell(snakeHead).setJump(new Jump(snakeHead, snakeTail));
            noOfSnakes--;
        }
        while(noOfLadders!=0) {
            int ladderHead= ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int ladderTail= ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            if(ladderHead>=ladderTail) {
                continue;
            }
            getCell(ladderHead).setJump(new Jump(ladderHead, ladderTail));
            noOfLadders--;
        }
    }

    public Cell getCell(int num) {
        int x=cells.length-1-(num/10);
        int y;
        if(x%2!=0) y=num%10;
        else y=cells.length-1-(num%10);
        return cells[x][y];
    }

    public void printBoard() {
        for (Cell[] cell : cells) {
            System.out.println(Arrays.toString(cell));
        }
    }
}
