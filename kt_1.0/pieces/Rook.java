package pieces;

import board.*;
import java.util.LinkedList;
import java.util.List;

public class Rook extends Piece {
    private int[] position; // the piece's current position
    private String alliance; // white or black
    private Board board;
    private List<int[]> legalMoves;

    public Rook(int[] position, String alliance, Board board) {
        super(position, alliance, board);
    }

    /**
     * Generates a list of the piece's legal moves given its current position.
     * @return the integer array list of legal moves
     */
    private List<int[]> legalMoves() {
        // stores all legal moves
        this.legalMoves = new LinkedList<>();
        int row = this.position[0];
        int col = this.position[1];

        // check each straight direction
        checkDirection(row,col,-1,0); // check straight up
        checkDirection(row,col,1,0); // check straight down
        checkDirection(row,col,0,-1); // check straight left
        checkDirection(row,col,0,1); // check straight right

        return this.legalMoves;
    }

    /**
     * Checks all vertical and horizontal tiles from the piece to see if a legal move can be made.
     * Re-used from the Bishop class.
     * @param row           the piece's row coordinate
     * @param col           the piece's col coordinate
     * @param rowIncr  the vertical direction (up/down)
     * @param colIncr  the horizontal direction (right/left)
     */
    private void checkDirection(int row, int col, int rowIncr, int colIncr) {
        boolean meetsOpposite = false;
        // initially check within the bounds of the chess board
        if (0 <= row+rowIncr && row+rowIncr <= board.rows && 0 <= col+colIncr && col+colIncr <= board.cols) {
            row += rowIncr;
            col += colIncr;
            // if unoccupied or if occupied by opposing alliance
            while (!this.board.getTile(row,col).checkIfOccupied() || !meetsOpposite &&
                    !this.board.getTile(row,col).getPiece().getAlliance().equals(this.alliance)) {
                // prevent this piece from being able to jump over an opposing alliance piece
                if (!this.board.getTile(row,col).getPiece().getAlliance().equals(this.alliance)) {
                    meetsOpposite = true;
                }
                this.legalMoves.add(this.board.getTile(row,col).getCoords());

                // look at the next diagonal tile
                if (0 <= row+rowIncr && row+rowIncr <= board.rows && 0 <= col+colIncr && col+colIncr <= board.cols) {
                    row += rowIncr;
                    col += colIncr;
                }
                else break;
            }
        }
    }

    /** Set the new position of the moved piece. */
    private void setPosition(int row, int col) {
        this.position[0] = row;
        this.position[1] = col;
    }

}
