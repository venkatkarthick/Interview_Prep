package LLD.ConceptAndCoding.ZZZ_Examples.A_TicTacToeLLD.Models;

public class PlayingPiece {
    PieceType piece;
    PlayingPiece(PieceType piece) {
        this.piece=piece;
    }

    @Override
    public String toString() {
        return piece.toString();
    }
}
