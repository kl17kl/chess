package player;

import board.*;
import pieces.*;

import java.util.List;

public abstract class Player {

    private String alliance; // white/black
    private String boardSide; // top/bot
    private String type; // comp/human
    private Board board;
    private List<Piece> activePieces;
    private String state; // check/checkmate/stalemate/castled
    private Boolean enpassFlag; //En passant flag

    public Player(String alliance, String boardSide, String type, Board board) {
        this.alliance = alliance;
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
    public void setFlag(boolean val) {
        this.enpassFlag = val;
    }
    public boolean getFlag() {
        return this.enpassFlag; 
    }

    /** Generate the player's pieces based on if they are on the top or bottom side of the board. */
    private void generatePieces(int pawnRow, int pieceRow) {
        // add all the pawns to the list
        for (int i = 0; i< board.cols; i++) {
            int coords[] = new int[]{pawnRow,i};
            this.activePieces.add(i, new Pawn(coords, alliance, board));
        }
        // add the remaining pieces to the list
        int i = 8;
        for (int j = 0; j < board.cols; j++) {
            int coords[] = new int[]{pieceRow, j};
            switch (j) {
                case (0):
                case (7):
                    this.activePieces.add(i, new Rook(coords, alliance, board));
                    break;
                case (1):
                case (6):
                    this.activePieces.add(i, new Knight(coords, alliance, board));
                    break;
                case (2):
                case (5):
                    this.activePieces.add(i, new Bishop(coords, alliance, board));
                    break;
                case (3):
                    this.activePieces.add(i, new Queen(coords, alliance, board));
                    break;
                case(4):
                    this.activePieces.add(i, new King(coords, alliance, board));
                    break;
                default:
                    break;
            }
            i++;
        }
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
