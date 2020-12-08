package board;

import pieces.*;

public class EmptyTile extends Tile{
    private int[] coords; //the tile's coordinates

    public EmptyTile(int[] coordinates) {
        this.coords = coordinates;
    }

    /** Checks to see if the tile is occupied by another piece. */
    @Override
    public boolean checkIfOccupied() {
        return false;
    }

    /** Returns the piece at the tile's coordinates. */
    @Override
    public Piece getPiece() {
        return null;
    }

    @Override
    public int[] getCoords() { return this.coords; }

}
