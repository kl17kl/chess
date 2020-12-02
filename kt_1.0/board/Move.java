package board;
import pieces.*;
import player.*;
import java.util.*;

public class Move {

    private Piece piece;
    private Board board;
    private Tile destination;
    private boolean isLegal;

    public Move(Board board, Piece piece, Tile destination) {
        this.board = board;
        this.piece = piece;
        this.destination = destination;
    }

    /**
     * This method executes the move by creating a new board with the move in place.
     * @return the new board with the move in place
     */
    public Board movePiece() {
        // check if opponent can perform en-passant
        checkEnPassant();

        Board newBoard = new Board(board.getP1(),board.getP2());
        newBoard = this.board;

        for (int[] move : this.piece.legalMoves()) {
            // if destination is legal destination
            if (Arrays.equals(move, destination.getCoords())) {
                this.isLegal = true;

                // set empty tile at piece's original position
                int[] pos = this.piece.getPosition();
                EmptyTile tile = new EmptyTile(pos);
                newBoard.setTile(tile, pos);

                // update destination tile with piece
                OccupiedTile tile2 = new OccupiedTile(this.destination.getCoords());
                newBoard.setTile(tile2, this.destination.getCoords());

                return newBoard;
            }
            else this.isLegal = false;
        }
        return null;
    }

    public boolean isMoveLegal() {
        return this.isLegal;
    }
    
    /** If the player makes a +2 Pawn move, then for each adjacent enemy pawn, allow them to perform en-passant. */
    private void checkEnPassant() {
        // if player is moving their Pawn by +2 tiles
        if (this.piece.getName().equals("Pawn")) {
            int[] pawntemp = this.piece.getPosition();
            int[] temp = this.destination.getCoords();
            if (temp[0] - pawntemp[0] == -2 || temp[0] - pawntemp[0] == 2) {
                // check right adjacent side for opponent Pawn
                if (temp[1] + 1 <= this.board.cols) {
                    if (this.board.getTile(temp[0], temp[1] + 1).getPiece().getName().equals("Pawn")
                            && !this.board.getTile(temp[0], temp[1] + 1).getPiece().getAlliance().equals(this.piece.getAlliance())) {
                        // get the players
                        Player p1 = this.board.getP1();
                        Player p2 = this.board.getP2();
                        // set opponent's en-passant flag as true
                        if (p1.getAlliance().equals(this.piece.getAlliance())) p2.setFlag(true);
                        else p1.setFlag(true);
                    }
                }
                // check left adjacent side for opponent Pawn
                if (temp[1] + 1 <= this.board.cols) {
                    if (this.board.getTile(temp[0], temp[1] + 1).getPiece().getName().equals("Pawn")
                            && !this.board.getTile(temp[0], temp[1] + 1).getPiece().getAlliance().equals(this.piece.getAlliance())) {
                        // get the players
                        Player p1 = this.board.getP1();
                        Player p2 = this.board.getP2();
                        // set opponent's en-passant flag as true
                        if (p1.getAlliance().equals(this.piece.getAlliance())) p2.setFlag(true);
                        else p1.setFlag(true);
                    }
                }
            }
        }
    }

    public void checkPromotion() {

    }

    public void checkCastled() {

    }


}
