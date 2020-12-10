package pieces;

import board.*;
import java.util.LinkedList;
import java.util.List;

public class Bishop extends Piece {
    private int[] position;     // the piece's current position
    private String alliance;    // white or black
    private Board board;
    private List<int[]> legalMoves;

    public Bishop(int[] position, String alliance, Board board) {
        this.position = position;
        this.alliance = alliance;
        this.board = board;
    }

    @Override
    public String getName() {
        return "Bishop";
    }

    @Override
    public String getAlliance() {
        return this.alliance;
    }

    @Override
    public void setPosition(int row, int col) {
        this.position[0] = row;
        this.position[1] = col;
    }

    @Override
    public int[] getPosition() {
        return this.position;
    }

    /**
     * Generates a list of the piece's legal moves given its current position.
     * @return the integer array list of legal moves
     */
    @Override
    public void legalMoves() {
        legalMoves = new LinkedList<>();
        // stores all legal moves
       // legalMoves = new LinkedList<>();
        int row = this.position[0];
        int col = this.position[1];

        // check each diagonal direction
        checkDiagonals(row,col,-1,-1); // check NW direction
        checkDiagonals(row,col,-1,1);  // check NE direction
        checkDiagonals(row,col,1,1);   // check SE direction
        checkDiagonals(row,col,1,-1);  // check SW direction
    }

    /**
     * Checks all diagonal tiles in a certain direction to see if a legal move can be made.
     * @param row           the piece's row coordinate
     * @param col           the piece's col coordinate
     * @param rowIncr  the vertical direction of the diagonal (up/down)
     * @param colIncr  the horizontal direction of the diagonal (right/left)
     */
    private void checkDiagonals(int row, int col, int rowIncr, int colIncr) {
        // initially check within the bounds of the chess board
        while (0 <= row+rowIncr && row+rowIncr <= 7 && 0 <= col+colIncr && col+colIncr <= 7) {
            // if empty tile
            if (!this.board.getTile(row + rowIncr, col + colIncr).checkIfOccupied()) {
                this.legalMoves.add(new int[]{row + rowIncr, col + colIncr});
            }
            // attacking enemy piece
            if (this.board.getTile(row + rowIncr, col + colIncr).checkIfOccupied()
                    && !this.board.getTile(row + rowIncr, col + colIncr).getPiece().getAlliance().equals(this.alliance) &&
                    !this.board.getTile(row + rowIncr, col + colIncr).getPiece().getName().equals("King")) {
                this.legalMoves.add(new int[]{row + rowIncr, col + colIncr});
                break;
            }
            if (this.board.getTile(row + rowIncr, col + colIncr).checkIfOccupied()
                    && this.board.getTile(row + rowIncr, col + colIncr).getPiece().getAlliance().equals(this.alliance))
                break;
            // look at the next diagonal tile
            row += rowIncr;
            col += colIncr;
        }
    }
    public List<int[]> getLegalMoves() {
        return this.legalMoves;
    }

    public void setNewLegals(List<int[]> legals) {
        this.legalMoves = legals;
    }
}
