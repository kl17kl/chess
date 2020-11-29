package pieces;

import board.*;
import java.util.List;

public abstract class Piece {
    private int[] position; // the piece's current position
    private String alliance; // white or black
    private Board board;

    public Piece(int[] position, String alliance, Board board) {
        this.position = position;
        this.alliance = alliance;
        this.board = board;
    }

    /** Generates a list of the piece's legal moves given its current position. */
    public List<int[]> legalMoves() {
        return null;
    }

    /** Gets the alliance of the piece. */
    public String getAlliance() {
        return this.alliance;
    }

    /** Set the position of the moved piece. */
    public void setPosition(int row, int col) {
        this.position[0] = row;
        this.position[1] = col;
    }

}
