package board;

import pieces.*;

public abstract class Tile {
    private int[] coords; //the tile's coordinates

    public Tile(int[] coordinates) {
        this.coords = coordinates;
    }

    /** Checks to see if the tile is occupied by another piece. */
    public boolean checkIfOccupied() {
        return true;
    }

    /** Returns the piece at the tile's coordinates. */
    public Piece getPiece() {
        return null;
    }

    /** Returns the tile's coordinates. */
    public int[] getCoords() {
        return this.coords;
    }

}
