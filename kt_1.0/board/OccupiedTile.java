package board;

import pieces.*;

public class OccupiedTile extends Tile{
    private int[] coords; //the tile's coordinates
    private Piece piece;

    public OccupiedTile(int[] coordinates) {
        this.coords = coordinates;
    }

    /** Checks to see if the tile is occupied by another piece. */
    @Override
    public boolean checkIfOccupied() {
        return true;
    }

    /** Returns the piece at the tile's coordinates. */
    @Override
    public Piece getPiece() {
        return this.piece;
    }

    /** Sets the piece at the tile's coordinates. */
    @Override
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public int[] getCoords() { return this.coords; }

}
