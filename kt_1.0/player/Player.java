package player;

import board.*;
import pieces.*;
import java.util.*;

public abstract class Player {

    private String boardSide; // top/bot
    private String type; // comp/human
    private Board board;
    private List<Piece> activePieces;
    private String state; // check/checkmate/stalemate/castled

    public Player(String boardSide, String type, Board board, List<Move> legalMoves) {
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
        this.state = null;
    }

    /** Generate the player's pieces based on if they are on the top or bottom side of the board. */
    private void generatePieces(int pawnRow, int pieceRow) {
    }

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
    public boolean isMoveLegal(int[] move, Board board) {
        return false;
    }

    /** Performs a pawn jump for the player and updates the opponent's pawn's legal moves. */
    public Board doPawnJump(int[] move, Board board) { return board; }

    /** Performs the pawn promotion and updates the board. */
    public Board doPromotion(int promoChoice, int[] move, Board board) { return board; }

    /** Executes en-passant. */
    public Board doEnPassant(int[] move, Board board) { return board; }

    /** The player makes their move. */
    public Board makeMove(int[] move, Board board) { return board;}

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
