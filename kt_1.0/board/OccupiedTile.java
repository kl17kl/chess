package board;

import pieces.*;

public class OccupiedTile extends Tile{
    private int[] coords; //the tile's coordinates
    private Piece piece;

    public OccupiedTile(int[] coordinates) {
        super(coordinates);
    }

    /** Checks to see if the tile is occupied by another piece. */
    public boolean checkIfOccupied() {
        return true;
    }

    /** Returns the piece at the tile's coordinates. */
    public Piece getPiece() {
        return this.piece;
    }

}
