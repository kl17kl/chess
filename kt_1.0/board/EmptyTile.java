package board;

import pieces.*;

public class EmptyTile extends Tile{
    private int[] coords; //the tile's coordinates

    public EmptyTile(int[] coordinates) {
        super(coordinates);
    }

    /** Checks to see if the tile is occupied by another piece. */
    public boolean checkIfOccupied() {
        return false;
    }

    /** Returns the piece at the tile's coordinates. */
    public Piece getPiece() {
        return null;
    }

}
