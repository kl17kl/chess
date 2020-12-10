package pieces;

import board.*;
import player.*;
import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece {
    private int[] position;     // the piece's current position
    private String alliance;    // white or black
    private Board board;
    private List<int[]> legalMoves;
    private int moveDirection;  // the side the pawn starts at (top/bot)
    private Boolean enpassFlag; // en-passant flag

    public Pawn(int[] position, String alliance, Board board) {
        this.position = position;
        this.alliance = alliance;
        this.board = board;
        this.enpassFlag = false;
    }

    @Override
    public String getName() {
        return "Pawn";
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

    @Override
    public void setFlag(boolean val) {
        this.enpassFlag = val;
    }

    @Override
    public boolean getFlag() {
        return this.enpassFlag;
    }

    /**
     * Calculates the list of the piece's legal moves given its current position.
     * @return the integer array list of legal moves
     */
    @Override
    public void legalMoves() {
        // determine our move direction
        legalMoves = new LinkedList<>();

        if (this.board.getP1().getAlliance().equals(this.alliance)) this.moveDirection = -1;
        else this.moveDirection = 1;

        // stores all legal moves
        int row = this.position[0];
        int col = this.position[1];

        // starting pawn move conditions
        if (row == 1 || row == 6) {
            // check if pawn can move +2 tiles
            if (row+2*moveDirection <= 7 && row+2*moveDirection >= 0 && !this.board.getTile(row+2*moveDirection,col).checkIfOccupied()) {
                this.legalMoves.add(this.board.getTile(row+2*moveDirection,col).getCoords());
            }
        }
        // check if pawn can move +1 tile and is within bounds of the chess board
        if (0 <= row+moveDirection && row+moveDirection <= 7) {
            if (!this.board.getTile(row+moveDirection,col).checkIfOccupied()) {
                this.legalMoves.add(this.board.getTile(row+moveDirection, col).getCoords());
            }
        }
        // check if pawn can move through an attack and is within bounds of the chess board
        if (0 <= col - 1 && row+moveDirection >= 0 && row+moveDirection<= 7 &&
                this.board.getTile(row + moveDirection, col - 1).checkIfOccupied()) {
            if (!this.board.getTile(row + moveDirection, col - 1).getPiece().getAlliance().equals(this.alliance) &&
                    !this.board.getTile(row + moveDirection, col-1).getPiece().getName().equals("King")) {
                this.legalMoves.add(this.board.getTile(row+moveDirection, col-1).getCoords());
            }
        }
        if (col + 1 <= 7 && row+moveDirection >= 0 && row+moveDirection<= 7
                && this.board.getTile(row + moveDirection, col + 1).checkIfOccupied()) {
            if (!this.board.getTile(row + moveDirection, col + 1).getPiece().getAlliance().equals(this.alliance) &&
                    !this.board.getTile(row + moveDirection, col+1).getPiece().getName().equals("King")) {
                this.legalMoves.add(this.board.getTile(row+moveDirection, col+1).getCoords());
            }
        }
        // check if pawn can do en-passant
        if (0<= col - 1 && this.board.getTile(row, col-1).checkIfOccupied()
                && this.board.getTile(row, col-1).getPiece().getName().equals("Pawn") && this.board.getTile(row, col-1).getPiece().getFlag()) {
            if (row == 3) this.legalMoves.add(this.board.getTile(2,col - 1).getCoords());
            if (row == 4) this.legalMoves.add(this.board.getTile(5,col - 1).getCoords());
        }
        if (col + 1 <= 7 && this.board.getTile(row, col+1).checkIfOccupied()
                && this.board.getTile(row, col+1).getPiece().getName().equals("Pawn") && this.board.getTile(row, col+1).getPiece().getFlag()) {
            if (row == 3) this.legalMoves.add(this.board.getTile(2,col + 1).getCoords());
            if (row == 4) this.legalMoves.add(this.board.getTile(5,col + 1).getCoords());
        }
    }

    public List<int[]> getLegalMoves() {
        return this.legalMoves;
    }

    public void setNewLegals(List<int[]> legals) {
        this.legalMoves = legals;
    }
}
