package pieces;

import board.*;
import player.*;
import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece {
    private int[] position; // the piece's current position
    private String alliance; // white or black
    private Board board;
    private List<int[]> legalMoves;
    private boolean hasMoved; // true if a pawn has moved from its starting position
    private int moveDirection; // the side the pawn starts at (top/bot)

    public Pawn(int[] position, String alliance, Board board) {
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

        // starting pawn move conditions
        if (!this.hasMoved) {
            // check if pawn can move +2 tiles
            if (!this.board.getTile(row+2*moveDirection,col).checkIfOccupied()) {
                this.legalMoves.add(this.board.getTile(row,col).getCoords());
            }
            this.hasMoved = true;
        }
        // check if pawn can move +1 tile and is within bounds of the chess board
        if (0 <= row+moveDirection && row+moveDirection <= board.rows) {
            if (!this.board.getTile(row+moveDirection,col).checkIfOccupied()) {
                this.legalMoves.add(this.board.getTile(row, col).getCoords());

                // check if pawn can move through an attack and is within bounds of the chess board
                if (0 <= col - 1) {
                    if (!this.board.getTile(row + moveDirection, col - 1).getPiece().getAlliance().equals(this.alliance)) {
                        this.legalMoves.add(this.board.getTile(row, col).getCoords());
                    }
                }
                if (col + 1 <= board.cols) {
                    if (!this.board.getTile(row + moveDirection, col + 1).getPiece().getAlliance().equals(this.alliance)) {
                        this.legalMoves.add(this.board.getTile(row, col).getCoords());
                    }
                }
            }
        }
        return this.legalMoves;
    }

    /** Set the pawn's board side as top or bottom so it knows which direction to move in. */
    public void setMoveDirection(Board board, Player player) {
        if (player.getBoardSide().equals("top")) {
            this.moveDirection = 1;
        }
        else {
            this.moveDirection = -1;
        }
    }

}
