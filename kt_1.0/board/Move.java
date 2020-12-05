package board;

import pieces.*;
import player.*;
import java.util.*;

public class Move {

    private Piece piece;
    private Board board;
    private int[] destination;
    private boolean isLegal;

    public Move(Board board, Piece piece, int[] destination) {
        this.board = board;
        this.piece = piece;
        this.destination = destination;
    }

    /**
     * This method executes the move by creating a new board with the move in place.
     * @return the new board with the move in place
     */
    public Board movePiece() {
        Board newBoard = this.board;
        Tile destinationTile = this.board.getTile(this.destination[2],this.destination[3]);
        Tile initialTile = this.board.getTile(this.destination[0],this.destination[1]);

        // if an attack move, add dead piece to board's list of dead pieces
        if (destinationTile.checkIfOccupied() && !destinationTile.getPiece().getAlliance().equals(initialTile.getPiece().getAlliance())) {
            newBoard.addDeadPiece(destinationTile.getPiece());
        }
        // update destination tile with moved piece
        Piece movedPiece = destinationTile.getPiece();
        newBoard.getTile(this.destination[2],this.destination[3]).setPiece(movedPiece);

        // set empty tile at piece's initial tile
        int[] startCoords = new int[]{this.destination[0],this.destination[1]};
        newBoard.setTile(new EmptyTile(startCoords),startCoords);
        return newBoard;
    }

    public void checkCastled() {

    }


}
