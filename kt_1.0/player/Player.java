package player;

import board.*;
import pieces.*;

import java.util.List;

public abstract class Player {

    private String boardSide;   // top/bot
    private String type;        // comp/human
    private Board board;
    private List<Moves> legalMoves;
    private List<Piece> pieces;
    private boolean isInCheck;
    private boolean castled;

    public Player(String boardSide, String type, Board board, List<Moves> legalMoves) {
        this.boardSide = boardSide;
        this.type = type;
        this.board = board;
        // generate the player's list of active pieces
        if (boardSide.equals("top")) {
            generatePieces(1,0);
        }
        else {
            generatePieces(6,7);
        }
    }

    /** Generate the player's pieces based on if they are on the top or bottom side of the board. */
    abstract void generatePieces(int pawnRow, int pieceRow);

    /** Get the alliance of the player (white/black) */
    public String getAlliance() {
        return null;
    }

    /** Get the side of the board of the player (top/bot). */
    public String getBoardSide() {
        return null;
    }

    /** Get the type of the player (comp/human). */
    public String getType() {
        return null;
    }

    /** Get the player's list of active pieces. */
    public List<Piece> getActivePieces() {
        return null;
    }

    /** Get the player's active pieces at a given position. */
    public Piece getActivePiece(int index) {
        return null;
    }

    /** Check if the player's move is a legal move. */
    public boolean isMoveLegal() {
        return false;
    }

    /** The player makes their move. */
    public void makeMove() {}

    /** Check if the player is in check. */
    public boolean isInCheck() {
        return false;
    }

    /** Check if the player is in checkmate. */
    public boolean isInCheckMate() {
        return false;
    }

    /** Check if the player is in stalemate. */
    public boolean isInStaleMate() {
        return false;
    }

    /** Check if the player is castled. */
    public boolean isCastled() {
        return false;
    }

}
package player;

import board.*;
import pieces.*;

import java.util.List;

public abstract class Player {

    private String boardSide;   // top/bot
    private String type;        // comp/human
    private Board board;
    private List<Moves> legalMoves;
    private List<Piece> pieces;
    private boolean isInCheck;
    private boolean castled;

    public Player(String boardSide, String type, Board board, List<Moves> legalMoves) {
        this.boardSide = boardSide;
        this.type = type;
        this.board = board;
        // generate the player's list of active pieces
        if (boardSide.equals("top")) {
            generatePieces(1,0);
        }
        else {
            generatePieces(6,7);
        }
    }

    /** Generate the player's pieces based on if they are on the top or bottom side of the board. */
    abstract void generatePieces(int pawnRow, int pieceRow);

    /** Get the alliance of the player (white/black) */
    public String getAlliance() {
        return null;
    }

    /** Get the side of the board of the player (top/bot). */
    public String getBoardSide() {
        return null;
    }

    /** Get the type of the player (comp/human). */
    public String getType() {
        return null;
    }

    /** Get the player's list of active pieces. */
    public List<Piece> getActivePieces() {
        return null;
    }

    /** Get the player's active pieces at a given position. */
    public Piece getActivePiece(int index) {
        return null;
    }

    /** Check if the player's move is a legal move. */
    public boolean isMoveLegal() {
        return false;
    }

    /** The player makes their move. */
    public void makeMove() {}

    /** Check if the player is in check. */
    public boolean isInCheck() {
        return false;
    }

    /** Check if the player is in checkmate. */
    public boolean isInCheckMate() {
        return false;
    }

    /** Check if the player is in stalemate. */
    public boolean isInStaleMate() {
        return false;
    }

    /** Check if the player is castled. */
    public boolean isCastled() {
        return false;
    }

}
