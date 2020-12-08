package player;

import board.*;
import pieces.*;
import java.util.*;

public class BlackPlayer extends Player {

    private String boardSide;   // top/bot
    private int playerNum;      // P1/P2
    private String type;        // comp/human
    private Board board;
    private List<Move> legalMoves;
    private List<Piece> pieces;

    public BlackPlayer(String boardSide, int playerNum, String type) {
        this.boardSide = boardSide;
        this.playerNum = playerNum;
        this.type = type;
        pieces = new LinkedList<>();
    }

    /** Generate the player's pieces based on if they are on the top or bottom side of the board. */
    @Override
    public void generatePieces(int pawnRow, int pieceRow, Board board) {
        // add all the pawns to the list
        for (int i = 0; i< 8; i++) {
            int coords[] = new int[]{pawnRow,i};
            this.pieces.add(i, new Pawn(coords, "black", board));
        }
        // add the remaining pieces to the list
        int i = 8;
        for (int j = 0; j < 8; j++) {
            int coords[] = new int[]{pieceRow, j};
            switch (j) {
                case (0):
                case (7):
                    this.pieces.add(i, new Rook(coords, "black", board));
                    break;
                case (1):
                case (6):
                    this.pieces.add(i, new Knight(coords, "black", board));
                    break;
                case (2):
                case (5):
                    this.pieces.add(i, new Bishop(coords, "black", board));
                    break;
                case (3):
                    this.pieces.add(i, new Queen(coords, "black", board));
                    break;
                case(4):
                    this.pieces.add(i, new King(coords, "black", board));
                    break;
                default:
                    break;
            }
            i++;
        }
    }

    public void setUpPieces(Board board) {
        // generate the player's list of active pieces
        if (this.boardSide.equals("top")) {
            generatePieces(1,0, board);
        }
        else {
            generatePieces(6,7, board);
        }
    }

    /** Get the player's list of active pieces. */
    @Override
    public List<Piece> getPieces() {
        return this.pieces;
    }

    /** Get the player's active pieces at a given position. */
    @Override
    public Piece getPiece(int index) {
        return this.pieces.get(index);
    }

    /** Get the alliance of the player (white/black) */
    @Override
    public String getAlliance() {
        return "black";
    }

    @Override
    public String getBoardSide() { return this.boardSide; }

    /** Check if the player is in check. */
    @Override
    public boolean isInCheck(Board board) {
        for (Piece opponentPiece : board.getOpponent(this).getPieces()) {
            for (int[] opponentMove : opponentPiece.legalMoves()) {
                if (opponentMove.equals(this.getPiece(12).getPosition())) return true;
            }
        }
        return false;
    }

    /** Check if the player is in checkmate. */
    @Override
    public boolean isInCheckMate(Board board) {
        return this.isInCheck(board) && !hasEscapeMoves();
    }

    /** Check if the player is in stalemate. */
    @Override
    public boolean isInStaleMate(Board board) {
        return !this.isInCheck(board) && !hasEscapeMoves();
    }

    @Override
    public boolean hasEscapeMoves() {
        // 1) Can we move the King out of check?
        List<int[]> kingMoves = this.getPiece(12).legalMoves();
        Player opponent = this.board.getOpponent(this);

        // check if King has any legal moves that aren't any of the opponent's legal moves
        for (Piece p : opponent.getPieces()) {
            for (int[] opponentMove : p.legalMoves()) {
                for (int[] kingMove : this.getPiece(12).legalMoves()) {
                    if (opponentMove.equals(kingMove)) kingMoves.remove(kingMove);
                }
            }
        }
        if (kingMoves.size() > 0) return true;
            // 2) Can we block the attacking piece from the King?
        else {
            for (Piece piece : this.getPieces()) {
                for (int[] move : piece.legalMoves()) {
                    for (Piece opponentPiece : opponent.getPieces()) {
                        for (int[] opponentMove : opponentPiece.legalMoves()) {
                            // if a piece's legal move = opponent piece's legal move
                            if (opponentMove.equals(move)) {
                                Board transitionBoard = this.board;
                                if (transitionBoard.getTile(move[0],move[1]).checkIfOccupied()) {
                                    transitionBoard.getTile(move[0],move[1]).setPiece(piece);
                                }
                                else {
                                    transitionBoard.setTile(new EmptyTile(piece.getPosition()),piece.getPosition());
                                }
                                if (!this.isInCheck(transitionBoard)) return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
