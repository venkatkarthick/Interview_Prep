package LLD.ConceptAndCoding.Examples.A_TicTacToeLLD;

import LLD.ConceptAndCoding.Examples.A_TicTacToeLLD.Models.Board;
import LLD.ConceptAndCoding.Examples.A_TicTacToeLLD.Models.PieceO;
import LLD.ConceptAndCoding.Examples.A_TicTacToeLLD.Models.PieceX;
import LLD.ConceptAndCoding.Examples.A_TicTacToeLLD.Models.Player;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {

    Deque<Player> players;
    Board board;

    Game() {
        initializeGame();
    }

    private void initializeGame() {
        Player player1=new Player("Player1", new PieceX());
        Player player2=new Player("Player2", new PieceO());
        players=new LinkedList<>();
        players.add(player1);
        players.add(player2);
        board=new Board(3);
    }

    public void startGame() {

        while(true) {
            board.displayBoard();
            Player playerTurn=players.removeFirst();

            System.out.println("Type your move "+playerTurn.name+" :");
            Scanner sc=new Scanner(System.in);
            String input=sc.nextLine();
            String[] inputArr=input.split(" ");
            int row= Integer.parseInt(inputArr[0]);
            int col=Integer.parseInt(inputArr[1]);

            if(!board.addPlayingPiece(row, col, playerTurn.piece)) {
                System.out.println("Error!!! Piece already occupied in entered row and col");
                players.addFirst(playerTurn);
                continue;
            }

            if(board.isWinner(row, col, playerTurn.piece)) {
                board.displayBoard();
                System.out.println("Hurray!!! "+playerTurn.name+" wins!!!!");
                break;
            }
            if(!board.hasFreeCells()) {
                System.out.println("Match ended in Tie!");
                break;
            }
            players.addLast(playerTurn);
        }

    }


}
