package board;
import pieces.*;
import java.util.*;

public abstract class Move {

    private Piece piece;
    private Board board;
    private Tile destination;

    public Move(Board board, Piece piece, Tile destination) {
        this.board = board;
        this.piece = piece;
        this.destination = destination;
    }

    /**
     * This method executes the move by creating a new board with the move in place.
     * @return the new board with the move in place
     */
    public Board movePiece() {

        List<Pawn> enemyPawns = checkEnPassant();
        Board newBoard = new Board(board.getP1(),board.getP2());
        newBoard = this.board;

        for (int[] move : this.piece.legalMoves()) {
            // if destination is legal destination
            if (Arrays.equals(move, destination.getCoords())) {

                // set empty tile at piece's original position
                int[] pos = this.piece.getPosition();
                EmptyTile tile = new EmptyTile(pos);
                newBoard.setTile(tile, pos);

                // update destination tile with piece
                OccupiedTile tile2 = new OccupiedTile(this.destination.getCoords());
                newBoard.setTile(tile2, this.destination.getCoords());

                // en-passant: if there are opponent Pawns adjacent to our moved Pawn
                if (enemyPawns.size()>0) {
                    for (Pawn p : enemyPawns) {
                        int[] coords = p.getPosition();
                        newBoard.getTile(coords[0],coords[1]).setPiece(p);
                    }
                }
                return newBoard;
            }
        }
        return null;
    }

    /** If the player makes a +2 Pawn move, then for each adjacent enemy pawn, allow them to perform en-passant. */
    private List<Pawn> checkEnPassant() {
        Pawn enemyPawnR, enemyPawnL;
        List<Pawn> enemyPawns = new LinkedList<>();

        if (this.piece.getName().equals("Pawn")) {
            int[] pawntemp = this.piece.getPosition();
            int[] temp = this.destination.getCoords();
            // player is moving their pawn by +2 tiles
            if (temp[0]-pawntemp[0] == -2 || temp[0]-pawntemp[0] == 2) {

                // check right adjacent side for opponent Pawn
                if (temp[1]+1 <= this.board.cols && this.board.getTile(temp[0],temp[1]+1).getPiece().getName().equals("Pawn")
                        && !this.board.getTile(temp[0],temp[1]+1).getPiece().getAlliance().equals(this.piece.getAlliance())) {
                    enemyPawnR = (Pawn) this.board.getTile(temp[0],temp[1]+1).getPiece();
                    // add the en-passant move as a legal move to the enemy's Pawn piece
                    if (pawntemp[1] == 6) pawntemp[1] = 5;
                    else if (pawntemp[1] == 1) pawntemp[1] = 2;
                    enemyPawnR.legalMoves().add(pawntemp);
                    enemyPawns.add(enemyPawnR);
                }
                // check left adjacent side for opponent Pawn
                if (temp[1]-1 <= this.board.cols && this.board.getTile(temp[0],temp[1]-1).getPiece().getName().equals("Pawn")
                        && !this.board.getTile(temp[0],temp[1]-1).getPiece().getAlliance().equals(this.piece.getAlliance())) {
                    enemyPawnL = (Pawn) this.board.getTile(temp[0],temp[1]-1).getPiece();
                    // add the en-passant move as a legal move to the enemy's Pawn piece
                    if (pawntemp[1] == 6) pawntemp[1] = 5;
                    else if (pawntemp[1] == 1) pawntemp[1] = 2;
                    enemyPawnL.legalMoves().add(pawntemp);
                    enemyPawns.add(enemyPawnL);
                }
            }
        }
        return enemyPawns;
    }


}
