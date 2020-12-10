package pieces;

import board.*;
import java.util.List;

public abstract class Piece {
    private int[] position; // the piece's current position
    private String alliance; // white or black
    private Board board;
    private Boolean enpassFlag; // En-passant flag
    private List<int[]> legalMoves;

    public Piece(int[] position, String alliance, Board board) {
        this.position = position;
        this.alliance = alliance;
        this.board = board;
    }

    protected Piece() {
    }

    public abstract String getName();

    /** Gets the alliance of the piece. */
    public abstract String getAlliance();

    /** Set the position of the moved piece. */
    public abstract void setPosition(int row, int col);

    /** Get the position of the piece. */
    public abstract int[] getPosition();

    /** Generates a list of the piece's legal moves given its current position. */
    public abstract void legalMoves();

    public abstract List<int[]> getLegalMoves();

    public abstract void setNewLegals(List<int[]> legals);

    public void setFlag(boolean val) {
        this.enpassFlag = false;
    }

    public boolean getFlag() {
        return false;
    }
}
