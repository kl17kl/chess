package player;

import board.*;
import pieces.*;
import java.util.*;

public class BlackPlayer extends Player {

    private String boardSide;   // top/bot
    private String type;        // comp/human
    private Board board;
    private List<Move> legalMoves;
    private List<Piece> pieces;
    private boolean isInCheck;
    private boolean castled;

    public BlackPlayer(String boardSide, String type, Board board, List<Move> legalMoves) {
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
            this.pieces.add(i, new Pawn(coords, "black", board));
        }
        // add the remaining pieces to the list
        int i = 8;
        for (int j = 0; j < board.cols; j++) {
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

    public Board doPawnJump(int[] move, Board board) {
        Pawn enemyPawn;
        // the player's Pawn piece
        Piece pawn = board.getTile(move[0],move[1]).getPiece();
        // check right adjacent side for opponent Pawn
        if (move[3] + 1 <= board.cols) {
            if (board.getTile(move[2], move[3] + 1).getPiece().getName().equals("Pawn")
                    && !board.getTile(move[2], move[3] + 1).getPiece().getAlliance().equals(pawn.getAlliance())) {
                // update the opponent pawn's legal moves
                enemyPawn = (Pawn) board.getTile(move[2], move[3] + 1).getPiece();
                if (move[0] == 6) move[0] = 5;
                else if (move[0] == 1) move[0] = 2;
                enemyPawn.setFlag(true);
                enemyPawn.legalMoves().add(move);
                board.getTile(move[2],move[3]+1).setPiece(enemyPawn);
            }
        }
        // check left adjacent side for opponent Pawn
        if (move[3] - 1 <= board.cols) {
            if (board.getTile(move[2], move[3] - 1).getPiece().getName().equals("Pawn")
                    && !board.getTile(move[2], move[3] - 1).getPiece().getAlliance().equals(pawn.getAlliance())) {
                // update the opponent pawn's legal moves
                enemyPawn = (Pawn) board.getTile(move[2], move[3] - 1).getPiece();
                enemyPawn.setFlag(true);
                enemyPawn.legalMoves().add(move);
                board.getTile(move[2],move[3]-1).setPiece(enemyPawn);
            }
        }
        // update the new board with the Pawn move
        int[] endCoords = new int[]{move[0],move[1]};
        int[] startCoords = new int[]{move[2],move[3]};
        board.setTile(new EmptyTile(startCoords),startCoords);
        board.setTile(new OccupiedTile(endCoords),endCoords);
        return board;
    }

    public Board doPromotion(int promoChoice, int[] move, Board board) {
        Pawn pawn = (Pawn) board.getTile(move[0],move[1]).getPiece();
        switch (promoChoice) {
            case (1):
                Queen queen = new Queen(pawn.getPosition(),pawn.getAlliance(),board);
                board.getTile(move[2],move[3]).setPiece(queen);
                break;
            case (2):
                Knight knight = new Knight(pawn.getPosition(),pawn.getAlliance(),board);
                board.getTile(move[2],move[3]).setPiece(knight);
                break;
            case (3):
                Rook rook = new Rook(pawn.getPosition(),pawn.getAlliance(),board);
                board.getTile(move[2],move[3]).setPiece(rook);
                break;
            case (4):
                Bishop bishop = new Bishop(pawn.getPosition(),pawn.getAlliance(),board);
                board.getTile(move[2],move[3]).setPiece(bishop);
                break;
            default:
                break;
        }
        // update pawn's initial position to empty
        int[] coords = new int[]{move[0],move[1]};
        board.setTile(new EmptyTile(coords),coords);
        return board;
    }

    /** Perform en-passant. Remove the opponent piece that we are passing. */
    public Board doEnPassant(int[] move, Board board) {
        Pawn pawn = (Pawn) board.getTile(move[0],move[1]).getPiece();
        // if we are on the top side
        if (move[2] == 2) {
            int[] emptyCoords = new int[]{move[2]+1,move[3]};
            board.getTile(move[2],move[3]).setPiece(pawn);
            board.setTile(new EmptyTile(emptyCoords),emptyCoords);
        }
        // if we are on the bottom side
        if (move[2] == 5) {
            int[] emptyCoords = new int[]{move[2]-1,move[3]};
            board.getTile(move[2],move[3]).setPiece(pawn);
            board.setTile(new EmptyTile(emptyCoords),emptyCoords);
        }
        return board;
    }

    /** Get the alliance of the player (white/black) */
    public String getAlliance() {
        return "black";
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

    public Board makeMove(int[] move, Board board) {
        Piece piece = board.getTile(move[0],move[1]).getPiece();
        Move aMove = new Move(board, piece, move);
        board = aMove.movePiece();
        return board;
    }

    private int getLetter(char letter) {
        int col = -1;
        switch (letter) {
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
        return col;
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
