package LLD.ConceptAndCoding.E_TicTacToeLLD.Models;

public class Board {
    int size;
    PlayingPiece[][] board;

    public Board(int size) {
        this.size=size;
        board=new PlayingPiece[size][size];
    }

    public boolean addPlayingPiece(int row, int col, PlayingPiece piece) {
        if(board[row][col]!=null) {
            return false;
        }
        board[row][col]=piece;
        return true;
    }

    public boolean isWinner(int row, int col, PlayingPiece piece) {

        boolean rowWinner=true;
        boolean colWinner=true;
        boolean diagonalWinner=true;
        boolean antidiagonalWinner=true;

        //row
        for (int i = 0; i < size; i++) {
            if(board[row][i]==null || board[row][i].piece!=piece.piece) {
                rowWinner=false;
            }
        }
        for (int i = 0; i < size; i++) {
            if(board[i][col]==null || board[i][col].piece!=piece.piece) {
                colWinner=false;
            }
        }
        for (int i = 0; i < size; i++) {
            if(board[i][i]==null || board[i][i].piece!=piece.piece) {
                diagonalWinner=false;
            }
        }
        for (int i = 0, j=size-1; i < size; i++, j--) {
            if(board[i][j]==null || board[i][j].piece!=piece.piece) {
                antidiagonalWinner=false;
            }
        }
        return rowWinner||colWinner||diagonalWinner||antidiagonalWinner;
    }

    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(board[i][j]==null) {
                    System.out.print("    |");
                } else {
                    System.out.print("   "+board[i][j].piece+"|");
                }
            }
            System.out.println();
        }
    }

    public boolean hasFreeCells() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(board[i][j]==null) return true;
            }
        }
        return false;
    }
}
