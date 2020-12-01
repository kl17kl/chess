package pieces;

import board.*;
import java.util.LinkedList;
import java.util.List;

public class King extends Piece {
    private int[] position; // the piece's current position
    private String alliance; // white or black
    private Board board;
    private List<int[]> legalMoves;

    public King(int[] position, String alliance, Board board) {
        super(position, alliance, board);
    }

    /**
     * Generates a list of the piece's legal moves given its current position.
     * @return the integer array list of legal moves
     */
    public List<int[]> legalMoves() {
        // stores all legal moves
        this.legalMoves = new LinkedList<>();
        int row = this.position[0];
        int col = this.position[1];

        // check all possible directions
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                // if within the bounds of the chess board
                if (0 <= row+i && row+i <= board.rows && 0 <= col+j && col+j <= board.cols) {
                    // if unoccupied or if occupied by opposing alliance
                    if (!this.board.getTile(i,j).checkIfOccupied() ||
                            !this.board.getTile(i,j).getPiece().getAlliance().equals(this.alliance)) {
                        this.legalMoves.add(this.board.getTile(i,j).getCoords());
                    }
                }
            }
        }
        return this.legalMoves;
    }

}
