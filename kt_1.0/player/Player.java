package player;

import board.*;
import pieces.*;
import java.util.*;

public abstract class Player {

    private String boardSide;   // top or bottom
    private int playerNum;      // P1 or P2
    private String type;        // computer or human
    private Board board;
    private List<Move> legalMoves;
    private List<Piece> pieces;

    public Player(String boardSide, int playerNum, String type) {
        this.boardSide = boardSide;
        this.playerNum = playerNum;
        this.type = type;
        pieces = new LinkedList<>();
    }

    protected Player() {
    }


    public abstract void setUpPieces(Board board);

    public abstract void addPiece(Piece piece);

    /** Generate the player's pieces based on if they are on the top or bottom side of the board. */
    public abstract void generatePieces(int pawnRow, int pieceRow, Board board);

    /** Get the alliance of the player (white/black) */
    public String getAlliance() {
        return null;
    }

    /** Get the side of the board of the player (top/bot). */
    public abstract String getBoardSide();

    /** Get the type of the player (comp/human). */
    public String getType() {
        return this.type;
    }

    /** Get the player number (P1/P2). */
    public int getPlayerNum() {
        if (this.playerNum == 1) return 1;
        else return 2;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    /** Get the player's list of active pieces. */
    public List<Piece> getPieces() {
        return this.pieces;
    }

    /** Get the player's active pieces at a given position. */
    public Piece getPiece(int index) {
        return this.pieces.get(index);
    }

    /** Check if the player's move is a legal move. */
    public boolean isMoveLegal(int[] move, Board board) {
        Piece piece = board.getTile(move[0],move[1]).getPiece();
        for (int[] pieceMove : piece.getLegalMoves()) {
            int[] destination = new int[]{move[2],move[3]};
            if (Arrays.equals(pieceMove, destination)) return true;
        }
        return false;
    }

    /** Performs a pawn jump for the player and updates the opponent's pawn's legal moves. */
    public Board doPawnJump(int[] move, Board board) {

        /*// the player's Pawn piece
        Piece pawn = board.getTile(move[0],move[1]).getPiece();
        // check right adjacent side for opponent Pawn
        if (move[3] + 1 <= 7) {
            if (board.getTile(move[2], move[3] + 1).checkIfOccupied() &&
                    board.getTile(move[2], move[3] + 1).getPiece().getName().equals("Pawn")
                    && !board.getTile(move[2], move[3] + 1).getPiece().getAlliance().equals(pawn.getAlliance())) {
                // update the opponent pawn's legal moves
                enemyPawn = (Pawn) board.getTile(move[2], move[3] + 1).getPiece();
                int x = 0;
                if (move[0] == 6) x = 5;
                else if (move[0] == 1) x = 2;
                int[] enemyMove = new int[]{x,move[1]};
                enemyPawn.setFlag(true);
                List<int[]> enemyLegals = enemyPawn.getLegalMoves();
                enemyLegals.add(enemyMove);
                enemyPawn.setNewLegals(enemyLegals);
                board.getTile(move[2],move[1]-1).setPiece(enemyPawn);
            }
        }
        // check left adjacent side for opponent Pawn
        if (0 <= move[3] - 1 ) {
            if (board.getTile(move[2], move[3] - 1).checkIfOccupied() &&
                    board.getTile(move[2], move[3] - 1).getPiece().getName().equals("Pawn")
                    && !board.getTile(move[2], move[3] - 1).getPiece().getAlliance().equals(pawn.getAlliance())) {
                // update the opponent pawn's legal moves
                enemyPawn = (Pawn) board.getTile(move[2], move[3] - 1).getPiece();
                int x = 0;
                if (move[0] == 6) x = 5;
                else if (move[0] == 1) x = 2;
                int[] enemyMove = new int[]{x,move[1]};
                enemyPawn.setFlag(true);
                List<int[]> enemyLegals = enemyPawn.getLegalMoves();
                enemyLegals.add(enemyMove);
                enemyPawn.setNewLegals(enemyLegals);
                board.getTile(move[2],move[1]+1).setPiece(enemyPawn);
            }
        }
        // update the new board with the Pawn move
        int[] startCoords = new int[]{move[0],move[1]};
        int[] endCoords = new int[]{move[2],move[3]};

        pawn.setPosition(move[2],move[3]);
        OccupiedTile aTile = new OccupiedTile(endCoords);
        aTile.setPiece(pawn);
        board.setTile(new EmptyTile(startCoords),startCoords);
        board.setTile(aTile,endCoords);
        return board;*/
        return board;
    }

    /** Performs the pawn promotion and updates the board. */
    public Board doPromotion(int promoChoice, int[] move, Board board, Player player) {
        Pawn pawn = (Pawn) board.getTile(move[0],move[1]).getPiece();
        OccupiedTile aTile;
        switch (promoChoice) {
            case (1):
                Queen queen = new Queen(pawn.getPosition(),pawn.getAlliance(),board);
                queen.setPosition(move[2],move[3]);
                player.addPiece(queen);
                aTile = new OccupiedTile(new int[]{move[2],move[3]});
                aTile.setPiece(queen);
                board.setTile(aTile,new int[]{move[2],move[3]});
                break;
            case (2):
                Knight knight = new Knight(pawn.getPosition(),pawn.getAlliance(),board);
                knight.setPosition(move[2],move[3]);
                player.addPiece(knight);
                aTile = new OccupiedTile(new int[]{move[2],move[3]});
                aTile.setPiece(knight);
                board.setTile(aTile,new int[]{move[2],move[3]});
                break;
            case (3):
                Rook rook = new Rook(pawn.getPosition(),pawn.getAlliance(),board);
                rook.setPosition(move[2],move[3]);
                player.addPiece(rook);
                aTile = new OccupiedTile(new int[]{move[2],move[3]});
                aTile.setPiece(rook);
                board.setTile(aTile,new int[]{move[2],move[3]});
                break;
            case (4):
                Bishop bishop = new Bishop(pawn.getPosition(),pawn.getAlliance(),board);
                bishop.setPosition(move[2],move[3]);
                player.addPiece(bishop);
                aTile = new OccupiedTile(new int[]{move[2],move[3]});
                aTile.setPiece(bishop);
                board.setTile(aTile,new int[]{move[2],move[3]});
                break;
            default:
                break;
        }
        // update pawn's initial position to empty
        int[] coords = new int[]{move[0],move[1]};
        board.setTile(new EmptyTile(coords),coords);
        return board;
    }

    /** Executes en-passant. */
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

    /** Perform the castle move. */
    public Board doCastling(int[] move, King king, Board board) {
        // set King's current position as empty
        int[] emptyCoords = new int[]{move[0],move[1]};
        board.setTile(new EmptyTile(emptyCoords),emptyCoords);
        // if castling with left Rook
        if (move[3] == 2) {
            Rook rook = (Rook) board.getTile(move[2], 0).getPiece();
            rook.setHasMoved();
            king.setHasMoved();
            OccupiedTile aTile = new OccupiedTile(new int[]{move[2],move[3]});
            aTile.setPiece(king);
            OccupiedTile aTile2 = new OccupiedTile(new int[]{move[2],3});
            aTile2.setPiece(rook);
            board.setTile(aTile,new int[]{move[2],move[3]});
            board.setTile(aTile2, new int[]{move[2],3});
            //remove rooks from initial position
            int[] emptyRooks = new int[]{move[2],0};
            board.setTile(new EmptyTile(emptyRooks),emptyRooks);

        }
        // if castling with right Rook
        if (move[3] == 6) {
            Rook rook = (Rook) board.getTile(move[2], 7).getPiece();
            rook.setHasMoved();
            king.setHasMoved();
            OccupiedTile aTile = new OccupiedTile(new int[]{move[2],move[3]});
            aTile.setPiece(king);
            OccupiedTile aTile2 = new OccupiedTile(new int[]{move[2],5});
            aTile2.setPiece(rook);
            board.setTile(aTile,new int[]{move[2],move[3]});
            board.setTile(aTile2, new int[]{move[2],5});
            //remove rooks from initial position
            int[] emptyRooks = new int[]{move[2],7};
            board.setTile(new EmptyTile(emptyRooks),emptyRooks);
        }
        return board;
    }

    /** The player makes their move. */
    public abstract Board makeMove(int[] move, Board board);

    public abstract boolean hasEscapeMoves(Board board);

    /** Check if the player is in check. */
    public abstract boolean isInCheck(Board board);

    /** Check if the player is in checkmate. */
    public abstract boolean isInCheckMate(Board board);

    /** Check if the player is in stalemate. */
    public abstract boolean isInStaleMate(Board board);
}
