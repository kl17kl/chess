package pieces;

import board.*;
import player.Player;

import java.util.LinkedList;
import java.util.List;

public class King extends Piece {
    private int[] position;     // the piece's current position
    private String alliance;    // white or black
    private Board board;
    private List<int[]> legalMoves;
    private boolean hasMoved;   // if King has made a move
    private String boardSide;   // top or bottom side

    public King(int[] position, String alliance, Board board) {
        this.position = position;
        this.alliance = alliance;
        this.board = board;
        this.hasMoved = false;
    }

    @Override
    public String getName() {
        return "King";
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
                        if (!this.board.getTile(i,j).getPiece().getName().equals("King")) {
                            this.legalMoves.add(this.board.getTile(i, j).getCoords());
                        }
                    }
                }
            }
        }
        checkCastling();
        return this.legalMoves;
    }

    public boolean hasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved() {
        this.hasMoved = true;
    }

    private void checkCastling() {
        if (!this.hasMoved) {
            // if we are castling on the top side with the left Rook
            if (this.board.getTile(0,0).getPiece().getName().equals("Rook")) {
                Rook rook = (Rook) this.board.getTile(0,0).getPiece();
                if (!rook.hasMoved() && !this.board.getTile(0,1).checkIfOccupied()
                        && !this.board.getTile(0,2).checkIfOccupied() && !this.board.getTile(0,3).checkIfOccupied()) {
                    this.legalMoves.add(new int[]{0,2});
                }
            } // if we are castling on the top side with the right Rook
            if (this.board.getTile(0,7).getPiece().getName().equals("Rook")) {
                Rook rook = (Rook) this.board.getTile(0,7).getPiece();
                if (!rook.hasMoved() && !this.board.getTile(0,5).checkIfOccupied() && !this.board.getTile(0,6).checkIfOccupied()) {
                    this.legalMoves.add(new int[]{0,6});
                }
            } // if we are castling on the bottom side with the left Rook
            if (this.board.getTile(7,0).getPiece().getName().equals("Rook")) {
                Rook rook = (Rook) this.board.getTile(7,0).getPiece();
                if (!rook.hasMoved() && !this.board.getTile(7,1).checkIfOccupied()
                        && !this.board.getTile(7,2).checkIfOccupied() && !this.board.getTile(7,3).checkIfOccupied()) {
                    this.legalMoves.add(new int[]{7,2});
                }
            } // if we are castling on the bottom side with the right Rook
            if (this.board.getTile(7,7).getPiece().getName().equals("Rook")) {
                Rook rook = (Rook) this.board.getTile(7,7).getPiece();
                // if Rook has not yet moved and tiles between Rook and King are empty
                if (!rook.hasMoved() && !this.board.getTile(7,5).checkIfOccupied() && !this.board.getTile(7,6).checkIfOccupied()) {
                    this.legalMoves.add(new int[]{7,6});
                }
            }
        }
    }

}
