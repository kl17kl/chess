package pieces;

import board.*;

import java.util.LinkedList;
import java.util.List;

public class Bishop extends Piece {
    private int[] position; // the piece's current position
    private String alliance; // white or black
    private Board board;

    public Bishop(int[] position, String alliance, Board board) {
        super(position, alliance, board);
    }

    /** Generates a list of the piece's legal moves given its current position */
    private List<int[]> legalMoves() {
        // stores all legal moves
        List<int[]> legalMoves = new LinkedList<>();

        // for each diagonal direction
        for (int i=0; i<4; i++) {
            int row = this.position[0];
            int col = this.position[1];

            for (int j=0; j<board.rows; j++) {
                for (int k=0; k<board.cols; k++) {
                    // if unoccupied
                    if (!board.getTile(j,k).checkIfOccupied()) {
                        legalMoves.add(board.getTile(j,k).getCoords());
                    }
                    // if occupied with the opposing alliance
                    else if (!board.getTile(j, k).getPiece().getAlliance().equals(this.alliance)) {
                        legalMoves.add(board.getTile(j,k).getCoords());
                    }
                }
            }
        }

        return legalMoves;
    }
}
