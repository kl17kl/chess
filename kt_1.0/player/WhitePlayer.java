package player;

import board.*;
import pieces.*;

import java.util.List;

public class WhitePlayer extends Player {

    private String boardSide;   // top/bot
    private String type;        // comp/human
    private Board board;
    private List<Moves> legalMoves;
    private List<Piece> pieces;
    private boolean isInCheck;
    private boolean castled;

    public WhitePlayer(String boardSide, String type, Board board, List<Moves> legalMoves) {
        super (boardSide, type, board, legalMoves);
        // generate the player's list of active pieces
        if (boardSide.equals("top")) {
            generatePieces(1,0);
        }
        else {
            generatePieces(6,7);
        }
        this.isInCheck = false;
    }

    /** Generate the player's pieces based on if they are on the top or bottom side of the board. */
    @Override
    public void generatePieces(int pawnRow, int pieceRow) {
        // add all the pawns to the list
        for (int i = 0; i< board.cols; i++) {
            int coords[] = new int[]{pawnRow,i};
            this.pieces.add(i, new Pawn(coords, "white", board));
        }
        // add the remaining pieces to the list
        int i = 8;
        for (int j = 0; j < board.cols; j++) {
            int coords[] = new int[]{pieceRow, j};
            switch (j) {
                case (0):
                case (7):
                    this.pieces.add(i, new Rook(coords, "white", board));
                    break;
                case (1):
                case (6):
                    this.pieces.add(i, new Knight(coords, "white", board));
                    break;
                case (2):
                case (5):
                    this.pieces.add(i, new Bishop(coords, "white", board));
                    break;
                case (3):
                    this.pieces.add(i, new Queen(coords, "white", board));
                    break;
                case(4):
                    this.pieces.add(i, new King(coords, "white", board));
                    break;
                default:
                    break;
            }
            i++;
        }
    }

    /** Get the alliance of the player (white/black) */
    public String getAlliance() {
        return "white";
    }

    /** Get the side of the board of the player (top/bot). */
    public String getBoardSide() {
        return this.boardSide;
    }

    /** Get the type of the player (comp/human). */
    public String getType() {
        return this.type;
    }

    /** Get the player's list of pieces. */
    public List<Piece> getPieces() {
        return this.pieces;
    }

    /** Get the player's piece at a given position. */
    public Piece getPiece(int index) {
        return this.pieces.get(index);
    }

    /** Check if the player's move is a legal move. */
    public boolean isMoveLegal(Move move) {
        return this.legalMoves.contains(move);
    }

    /** Check if player has any escape moves when in check. */
    protected boolean hasEscapeMoves() {
        for (final Moves move: this.legalMoves) {
            final MoveTransition transition = makeMove(move);
            if (transition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false;
    }

    /** Check if the player is in check state. */
    public boolean isInCheck(Board board) {
        return this.isInCheck;
    }

    /** Check if the player is in checkmate. */
    public boolean isInCheckMate(Board board) {
        return this.isInCheck && !hasEscapeMoves();
    }

    /** Check if the player is in stalemate. */
    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }

    /** Check if the player is castled. */
    public boolean isCastled() {
        return this.castled;
    }
}
