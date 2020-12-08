package board;

import pieces.*;

public abstract class Tile {
    private int[] coords; //the tile's coordinates

    public Tile(int[] coordinates) {
        this.coords = coordinates;
    }

    protected Tile() {
    }

    /** Checks to see if the tile is occupied by another piece. */
    public abstract boolean checkIfOccupied();

    /** Returns the piece at the tile's coordinates. */
    public abstract Piece getPiece();

    /** Sets the piece at the tile's coordinates. */
    public void setPiece(Piece piece) {}

    /** Returns the tile's coordinates. */
    public abstract int[] getCoords();
}
