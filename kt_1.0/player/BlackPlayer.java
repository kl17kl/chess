package player;

import board.*;
import pieces.*;

import java.util.List;

public class BlackPlayer extends Player {

    private String boardSide;   // top/bot
    private String type;        // comp/human
    private Board board;
    private List<Moves> legalMoves;
    private List<Piece> pieces;
    private boolean isInCheck;
    private boolean castled;

    public BlackPlayer(String boardSide, String type, Board board, List<Moves> legalMoves) {
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

    /** The player makes their move.
    public Board makeMove(Piece piece, Tile destinationTile, Board board) {
        Board newBoard = new Board(board.getP1(), board.getP2());
        // if the opponent is occupying the destination tile
        if (destinationTile.checkIfOccupied()) {
            AttackMove attack = new AttackMove();
            newBoard = attack.move(piece, destinationTile, board);


             ##this should belong to the attack move method:
             int[] initialCoords = piece.getPosition();
            int[] destinationCoords = tile.getPiece().getPosition();
            // move player's piece to their destination tile
            newBoard.getTile(destinationCoords[0],destinationCoords[1]).setPiece(piece);
            piece.setPosition(destinationCoords[0],destinationCoords[1]);
            // update opponent's attacked piece's status to dead
            Piece deadPiece = newBoard.getTile(destinationCoords[0],destinationCoords[1]).getPiece();
            newBoard.addDeadPiece(deadPiece);
            deadPiece.setState("dead");
            // remove piece from its initial position
            newBoard.setTile(new EmptyTile(initialCoords),initialCoords);

        } // if the piece is moving to an empty tile
        else {
           ActiveMove aMove = new ActiveMove();
           newBoard = aMove.move(piece, destinationTile, board);
        }
        return newBoard;
    } */

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
