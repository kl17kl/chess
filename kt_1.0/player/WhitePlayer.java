package player;

import board.*;
import pieces.*;

import java.util.List;

public class WhitePlayer extends Player {

    private String alliance; // white
    private String boardSide; // top/bot
    private String type; // comp/human
    private Board board;
    private List<Piece> pieces;
    private String state; // check/checkmate/stalemate/castled
    private Boolean enpassFlag;


    public WhitePlayer(String alliance, String boardSide, String type, Board board) {
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

    /** The player makes their move. */
    public void makeMove(Piece piece, String coords) {
        int col = -1;
        char letterCol = coords.charAt(0);
        char row = coords.charAt(1);
        //turn destination into coords
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
        aMove.setPlayer(this);
        aMove.movePiece();
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
