package player;

import board.*;
import pieces.*;

import java.util.List;

public class WhitePlayer extends Player {

    private String boardSide;   // top/bot
    private String type;        // comp/human
    private Board board;
    private List<Move> legalMoves;
    private List<Piece> pieces;
    private boolean isInCheck;
    private boolean castled;

    public WhitePlayer(String boardSide, String type, Board board, List<Move> legalMoves) {
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

    /** Check if player has any escape moves when in check. *//*
    protected boolean hasEscapeMoves() {
        for (final Move move: this.legalMoves) {
            final MoveTransition transition = makeMove(move);
            if (transition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false;
    }*/

    /** The player makes their move. */
    public void makeMove(Piece piece, String coords) {
        int col = -1;
        char letterCol = coords.charAt(0);
        char row = coords.charAt(1);
        //turn destination into coordinates
        switch (letterCol) {
            case ('A'):
                col = 0;
                break;
            case ('B'):
                col = 1;
                break;
            case ('C'):
                col = 2;
                break;
            case ('D'):
                col = 3;
                break;
            case ('E'):
                col = 4;
                break;
            case ('F'):
                col = 5;
                break;
            case ('G'):
                col = 6;
                break;
            case ('H'):
                col = 7;
                break;
            default:
                break;
        }
        int[] destination = new int[]{(int)row, col};
        Move aMove = new Move(this.board, piece, this.board.getTile(destination[0],destination[1]));
        aMove.movePiece();
    }

    /** Check if the player is in check state. */
    public boolean isInCheck(Board board) {
        return this.isInCheck;
    }

    /** Check if the player is in checkmate. *//*
    public boolean isInCheckMate(Board board) {
        return this.isInCheck && !hasEscapeMoves();
    }

    *//** Check if the player is in stalemate. *//*
    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }
*/
    /** Check if the player is castled. */
    public boolean isCastled() {
        return this.castled;
    }
}
