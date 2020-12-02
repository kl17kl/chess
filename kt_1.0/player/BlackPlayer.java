package player;

import board.*;
import pieces.*;

import java.util.List;

public class BlackPlayer extends Player {

    private String alliance; // black
    private String boardSide; // top/bot
    private String type; // comp/human
    private Board board;
    private List<Piece> pieces;
    private String state; // check/checkmate/stalemate/castled
    private Boolean enpassFlag;


    public BlackPlayer(String alliance, String boardSide, String type, Board board) {
        this.enpassFlag = false;
        super (alliance, boardSide, type, board);
        // generate the player's list of active pieces
        if (boardSide.equals("top")) {
            generatePieces(1,0);
        }
        else {
            generatePieces(6,7);
        }
        state = null;
    }

    /** Generate the player's pieces based on if they are on the top or bottom side of the board. */
    private void generatePieces(int pawnRow, int pieceRow) {
        // add all the pawns to the list
        for (int i = 0; i< board.cols; i++) {
            int coords[] = new int[]{pawnRow,i};
            this.pieces.add(i, new Pawn(coords, alliance, board));
        }
        // add the remaining pieces to the list
        int i = 8;
        for (int j = 0; j < board.cols; j++) {
            int coords[] = new int[]{pieceRow, j};
            switch (j) {
                case (0):
                case (7):
                    this.pieces.add(i, new Rook(coords, alliance, board));
                    break;
                case (1):
                case (6):
                    this.pieces.add(i, new Knight(coords, alliance, board));
                    break;
                case (2):
                case (5):
                    this.pieces.add(i, new Bishop(coords, alliance, board));
                    break;
                case (3):
                    this.pieces.add(i, new Queen(coords, alliance, board));
                    break;
                case(4):
                    this.pieces.add(i, new King(coords, alliance, board));
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
    public boolean isMoveLegal(Piece piece, Tile destinationTile) {
        // if player is in Check they must get out of check
        if (this.state.equals("check")) {
/////////////////AH. so much work.
        }
        if (piece.legalMoves().contains(destinationTile.getCoords())) {
            return true;
        }
        return false;
    }

    /** The player makes their move. */
    public Board makeMove(Piece piece, Tile destinationTile, Board board) {
        Board newBoard = new Board(board.getP1(), board.getP2());
        // if the opponent is occupying the destination tile
        if (destinationTile.checkIfOccupied()) {
            AttackMove attack = new AttackMove();
            newBoard = attack.move(piece, destinationTile, board);

            /**
             * ##this should belong to the attack move method:
             * int[] initialCoords = piece.getPosition();
            int[] destinationCoords = tile.getPiece().getPosition();
            // move player's piece to their destination tile
            newBoard.getTile(destinationCoords[0],destinationCoords[1]).setPiece(piece);
            piece.setPosition(destinationCoords[0],destinationCoords[1]);
            // update opponent's attacked piece's status to dead
            Piece deadPiece = newBoard.getTile(destinationCoords[0],destinationCoords[1]).getPiece();
            newBoard.addDeadPiece(deadPiece);
            deadPiece.setState("dead");
            // remove piece from its initial position
            newBoard.setTile(new EmptyTile(initialCoords),initialCoords);*/

        } // if the piece is moving to an empty tile
        else {
           ActiveMove aMove = new ActiveMove();
           newBoard = aMove.move(piece, destinationTile, board);
        }
        return newBoard;
    }

    /**
     * Check if the player is in check state.
     * @return true if the opponent is attacking the player's King piece with any of their pieces.
     */
    public boolean isInCheck(Board board) {
        if (!this.state.equals("checkMate") && !this.state.equals("staleMate")) {
            // get the opponent player
            Player player = (board.getP1().getAlliance().equals("white")) ? board.getP1() : board.getP2();
            // check if any opponent's pieces can attack the player's King
            for (Piece p : player.getActivePieces()) {
                if (p.legalMoves().equals(this.getPiece(12).getPosition())) {
                    this.state = "check";
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if the player is in checkmate.
     * @return true if the player's King piece is in check and cannot make any legal moves to break out of its state.
     */
    public boolean isInCheckMate(Board board) {
        // player must first be in Check
        if (this.state.equals("check")) {
            // get the opponent player
            Player player = (board.getP1().getAlliance().equals("white")) ? board.getP1() : board.getP2();
            // get the list of the player's King's legal moves
            List<int[]> kingsMoves = this.getPiece(12).legalMoves();
            // check for all opponent's pieces that can attack the player's King
            for (Piece p : player.getActivePieces()) {
                //if a piece's legal move is one of the king's legal moves - remove legal move from king
                kingsMoves.removeIf(moves -> p.legalMoves().contains(moves));
                if (kingsMoves.size()==0) {
                    this.state = "checkmate";
                    ////////////////this is HARD gotta check every piece - not just king - a piece can block for the king
                }
                if (p.legalMoves().) {
                    this.state = "check";
                    return true;
                }
            }
        }
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
