package board;

import pieces.*;

public abstract class Tile {
    private int[] coords; //the tile's coordinates
    private Piece piece;

    public Tile(int[] coordinates) {
        this.coords = coordinates;
    }

    /** Checks to see if the tile is occupied by another piece. */
    public boolean checkIfOccupied() {
        return true;
    }

    /** Sets the piece at the tile's coordinates. */
    public void setPiece(Piece piece) {
        this.piece = piece;
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
