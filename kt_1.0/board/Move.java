package board;
import pieces.*;
import player.*;
import java.util.*;

public class Move {

    private Piece piece;
    private Board board;
    private Tile destination;
    private boolean isLegal;
    private Player player;

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

        Board newBoard = new Board(board.getP1(),board.getP2());
        newBoard = this.board;
        List<Pawn> enemyPawns = new LinkedList<>();

        for (int[] move : this.piece.legalMoves()) {
            // if destination is legal destination
            if (Arrays.equals(move, destination.getCoords())) {
                this.isLegal = true;

                if (this.piece.getName().equals("Pawn")) {
                    enemyPawns = checkEnPassant();

                    // if player is performing an en-passant
                    int[] coords = this.destination.getCoords();
                    int[] piecePos = this.piece.getPosition();
                    if (this.piece.getFlag() && coords[1] != piecePos[1] && !this.destination.checkIfOccupied()) {
                        newBoard = doEnPassant(newBoard);
                    } // if doing a normal move
                    else {
                        // set empty tile at piece's original position
                        int[] pos = this.piece.getPosition();
                        EmptyTile tile = new EmptyTile(pos);
                        newBoard.setTile(tile, pos);

                        // update destination tile with piece
                        int[] coordss = this.destination.getCoords();
                        // if attack : add dead piece to board's list of dead pieces
                        newBoard.addDeadPiece(newBoard.getTile(coordss[0], coordss[1]).getPiece());
                        // update tile with moved piece
                        OccupiedTile tile2 = new OccupiedTile(this.destination.getCoords());
                        tile2.setPiece(this.piece);
                        newBoard.setTile(tile2, this.destination.getCoords());

                        // add opponent pawns to the board with their updated legal moves (for en-passant)
                        if (enemyPawns.size() > 0) {
                            for (Pawn p : enemyPawns) {
                                OccupiedTile aTile = new OccupiedTile(p.getPosition());
                                newBoard.setTile(aTile, p.getPosition());
                            }
                        }
                    }
                    for (Piece piece : this.player.getActivePieces()) {
                        piece.setFlag(false);
                    }
                    //checkPromotion();

                } // if not a pawn
                else {
                    // set empty tile at piece's original position
                    int[] pos = this.piece.getPosition();
                    EmptyTile tile = new EmptyTile(pos);
                    newBoard.setTile(tile, pos);

                    // update destination tile with piece
                    int[] coords = this.destination.getCoords();
                    // if attack : add dead piece to board's list of dead pieces
                    newBoard.addDeadPiece(newBoard.getTile(coords[0], coords[1]).getPiece());
                    // update tile with moved piece
                    OccupiedTile tile2 = new OccupiedTile(this.destination.getCoords());
                    tile2.setPiece(this.piece);
                    newBoard.setTile(tile2, this.destination.getCoords());

                    return newBoard;
                }
            }
            else this.isLegal = false;
        }
        return null;
    }

    public boolean isMoveLegal() {
        return this.isLegal;
    }

    /** If the player makes a +2 Pawn move, then for each adjacent enemy pawn, allow them to perform en-passant. */
    private List<Pawn> checkEnPassant() {
        List<Pawn> enemyPawns = new LinkedList<>();
        int[] pawntemp = this.piece.getPosition();
        int[] temp = this.destination.getCoords();
        // get the players
        Player p1 = this.board.getP1();
        Player p2 = this.board.getP2();

        // check if pawn moves +2 tiles
        if (temp[0] - pawntemp[0] == -2 || temp[0] - pawntemp[0] == 2) {
            // check right adjacent side for opponent Pawn
            if (temp[1] + 1 <= this.board.cols) {
                if (this.board.getTile(temp[0], temp[1] + 1).getPiece().getName().equals("Pawn")
                        && !this.board.getTile(temp[0], temp[1] + 1).getPiece().getAlliance().equals(this.piece.getAlliance())) {

                    Pawn enemyPawnR = (Pawn) this.board.getTile(temp[0], temp[1] + 1).getPiece();
                    if (pawntemp[1] == 6) pawntemp[1] = 5;
                    else if (pawntemp[1] == 1) pawntemp[1] = 2;
                    enemyPawnR.setFlag(true);
                    enemyPawnR.legalMoves().add(pawntemp);
                    enemyPawns.add(enemyPawnR);
                }
            }
            // check left adjacent side for opponent Pawn
            if (temp[1] - 1 <= this.board.cols) {
                if (this.board.getTile(temp[0], temp[1] - 1).getPiece().getName().equals("Pawn")
                        && !this.board.getTile(temp[0], temp[1] - 1).getPiece().getAlliance().equals(this.piece.getAlliance())) {

                    Pawn enemyPawnL = (Pawn) this.board.getTile(temp[0], temp[1] - 1).getPiece();
                    if (pawntemp[1] == 6) pawntemp[1] = 5;
                    else if (pawntemp[1] == 1) pawntemp[1] = 2;
                    enemyPawnL.legalMoves().add(pawntemp);
                    enemyPawnL.setFlag(true);
                    enemyPawns.add(enemyPawnL);
                }
            }
        }
        return enemyPawns;
    }

    public Board doEnPassant(Board newBoard) {
        //remove opponent piece thats below/above
        int[] coords = this.destination.getCoords();
        int[] newCoords;
        // if in top row
        if (coords[0] == 2) {
            newCoords = new int[]{coords[0]+1,coords[1]};
            OccupiedTile tile = new OccupiedTile(newCoords);
            newBoard.setTile(tile, newCoords);
        }
        // if in bottom row
        if (coords[0] == 5) {
            newCoords = new int[]{coords[0]-1,coords[1]};
            OccupiedTile tile = new OccupiedTile(newCoords);
            newBoard.setTile(tile, newCoords);
        }
        return newBoard;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean checkPromotion() {
        int[] coords = this.piece.getPosition();
        // if pawn has reached the end of the board
        if (coords[0] == 7 || coords[0] == 0) return true;
        else return false;
    }

    public void doPromotion(String piece) {
        int[] coords = this.piece.getPosition();

        switch (piece) {
            case ("Queen"):
                Queen queen = new Queen(coords, this.piece.getAlliance(), this.board);
                break;
            case ("Knight"):
                Knight knight = new Knight(coords, this.piece.getAlliance(), this.board);
                break;
            case ("Rook"):
                Rook rook = new Rook(coords, this.piece.getAlliance(), this.board);
                break;
            case ("Bishop"):
                Bishop bishop = new Bishop(coords, this.piece.getAlliance(), this.board);
                break;
            default:
                break;
        }
    }

    public void checkCastled() {

    }


}
