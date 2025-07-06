package LLD.ConceptAndCoding.ZZZ_Examples.C_SnakeAndLadders.Code;

import java.util.Deque;
import java.util.LinkedList;

public class Game {

    Board board;
    Dice dice;
    Deque<Player> players;
    Player winner;
    
    public Game() {
        initializeGame();
    }

    private void initializeGame() {
        board=new Board(10, 5, 5);
        dice=new Dice(2);
        players=new LinkedList<>();
        players.add(new Player(1, 0));
        players.add(new Player(2, 0));
    }


    public void startGame() {
        board.printBoard();
        while(true) {
            Player curPlayer=players.removeFirst();
            int move=dice.rollDice();
            System.out.println("Player "+curPlayer.playerId+" rolled "+move);
            curPlayer.currentPos=curPlayer.currentPos+move;
            if(curPlayer.currentPos>=board.cells.length*board.cells.length-1) {
                winner=curPlayer;
                System.out.println("Winner : " + winner);
                break;
            }
            Cell curCell=board.getCell(curPlayer.currentPos);
            if(curCell.jump!=null) {
                if(curPlayer.currentPos>curCell.jump.endPos) {
                    System.out.println("Oops!!! Player " +curPlayer.playerId+ " got hit by a snake!");
                } else {
                    System.out.println("Hurray!!! Player " +curPlayer.playerId+ " climbed a ladder up!");
                }
                curPlayer.currentPos=curCell.jump.endPos;
            }
            players.add(curPlayer);
            System.out.println(curPlayer);
        }
    }
}
