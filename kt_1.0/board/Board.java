package board;

import pieces.*;
import player.*;
import java.util.*;

public class Board {

    public int rows = 8, cols = 8;
    private Tile[][] boardTiles;
    private List<Piece> deadPieces; //the dead pieces
    private String whiteSide; // either top/bot side of the board
    private Player p1, p2;

    public Board(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /** Creates the board in its initial state. */
    public void createBoard() {
        // set-up the empty tiles
        for (int i=2; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                int[] coords = new int[]{i,j};
                boardTiles[i][j] = new EmptyTile(coords);
            }
        }
        // set-up the occupied tiles
        setUpBoardPieces();
        deadPieces = new LinkedList<>();
    }

    /** Set-up the initial board with the occupied tiles and their pieces. */
    private void setUpBoardPieces() {
        // set up top pawns
        for (int i=0; i<cols; i++) {
            int[] coords = new int[]{1,i};
            this.setTile(new OccupiedTile(coords),coords);
            this.getTile(1,i).setPiece(p2.getActivePiece(i));
        }
        // set up remaining top pieces
        for (int i=0; i<cols; i++) {
            int[] coords = new int[]{0,i};
            this.setTile(new OccupiedTile(coords),coords);
            this.getTile(0,i).setPiece(p2.getActivePiece(i+8));
        }
        // set up bottom pawns
        for (int i=0; i<cols; i++) {
            int[] coords = new int[]{6,i};
            this.setTile(new OccupiedTile(coords),coords);
            this.getTile(6,i).setPiece(p1.getActivePiece(i));
        }
        // set up remaining bottom pieces
        for (int i=0; i<cols; i++) {
            int[] coords = new int[]{7,i};
            this.setTile(new OccupiedTile(coords),coords);
            this.getTile(7,i).setPiece(p1.getActivePiece(i+8));
        }
    }

    /** Determine which side is the white side. */
    public void setWhiteSide(String side) {
        // if white player is on the top
        if (side.equals("top")) {
            this.whiteSide = "top";
        }
        else {
            this.whiteSide = "bot";
        }
    }

    /** Sets the board with all the white/black active pieces. */
    public void setPieces(List<Piece> pieces) {
        clearBoard();
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                int[] coords = new int[]{i,j};
                // set the board tile as empty..
                this.setTile(new EmptyTile(coords),coords);
                // ..unless a piece is positioned on that tile
                for (Piece p : pieces) {
                    if (Arrays.equals(p.getPosition(), coords)) {
                        OccupiedTile oTile = new OccupiedTile(coords);
                        this.setTile(oTile,coords);
                        oTile.setPiece(p);
                        break;
                    }
                }
            }
        }
    }

    /** Clears the board of all tiles and pieces. */
    private void clearBoard() {
        for (Tile[] t : boardTiles) {
            t = null;
        }
    }

    /** Set an empty tile at a given position. */
    public void setTile(EmptyTile tile, int[] position) {
        this.boardTiles[position[0]][position[1]] = new EmptyTile(position);
    }

    /** Set an occupied tile at a given position. */
    public void setTile(OccupiedTile tile, int[] position) {
        this.boardTiles[position[0]][position[1]] = new OccupiedTile(position);
    }

    /** Gets the tile at a given position. */
    public Tile getTile(int row, int col) {
        return this.boardTiles[row][col];
    }

    /** Returns the entire list of tiles. */
    public Tile[][] getBoardTiles() {
        return this.boardTiles;
    }

    /** Get player one. */
    public Player getP1() {
        return p1;
    }

    /** Get player two. */
    public Player getP2() {
        return p2;
    }

    /** Adds an attacked piece to the list of dead pieces. */
    public void addDeadPiece(Piece piece) {
        deadPieces.add(piece);
    }

}
