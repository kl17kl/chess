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

    public Board() {
       /* this.p1 = p1;
        this.p2 = p2;
        this.p1.setUpPieces();
        this.p2.setUpPieces();*/
        //createBoard();
    }

    /** Creates the board in its initial state. */
    public void createBoard() {
        boardTiles = new Tile[8][8];
        // set-up the empty tiles
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                int[] coords = new int[]{i,j};
                boardTiles[i][j] = new EmptyTile(coords);
            }
        }
        setUpBoardPieces();
        deadPieces = new LinkedList<>();
    }

    /** Set-up the initial board with the occupied tiles and their pieces. */
    private void setUpBoardPieces() {
        // set up top pawns
        for (int i=0; i<8; i++) {
            int[] coords = new int[]{1,i};
            this.setTile(new OccupiedTile(coords),coords);
            this.getTile(1,i).setPiece(this.p2.getPiece(i));
        }
        // set up remaining top pieces
        for (int i=0; i<8; i++) {
            int[] coords = new int[]{0,i};
            this.setTile(new OccupiedTile(coords),coords);
            this.getTile(0,i).setPiece(this.p2.getPiece(i+8));
        }
        // set up bottom pawns
        for (int i=0; i<8; i++) {
            int[] coords = new int[]{6,i};
            this.setTile(new OccupiedTile(coords),coords);
            this.getTile(6,i).setPiece(this.p1.getPiece(i));
        }
        // set up remaining bottom pieces
        for (int i=0; i<8; i++) {
            int[] coords = new int[]{7,i};
            this.setTile(new OccupiedTile(coords),coords);
            this.getTile(7,i).setPiece(this.p1.getPiece(i+8));
        }
    }

    /** Determine which side is the white side. */
    public void setWhiteSide(String side) {
        // if white player is on the top
        if (side.equals("top")) this.whiteSide = "top";
        else this.whiteSide = "bot";
    }

    /** Sets the board with all the white/black active pieces. */
    public void setPieces(List<Piece> pieces) {
        for (Tile[] t : boardTiles) t = null;
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

    //Convert piece name to a symbol representing each piece on the board
    private String convertPiece(String name, String alliance) {
        String acronym ="";
        switch (name) {
            case ("Bishop"):
                if (alliance.equals("white")) acronym = "wB";
                else acronym = "bB";
                break;
            case ("King"):
                if (alliance.equals("white")) acronym = "wK";
                else acronym = "bK";
                break;
            case ("Knight"):
                if (alliance.equals("white")) acronym = "wk";
                else acronym = "bk";
                break;
            case ("Pawn"):
                if (alliance.equals("white")) acronym = "wP";
                else acronym = "bP";
                break;
            case ("Queen"):
                if (alliance.equals("white")) acronym = "wQ";
                else acronym = "bQ";
                break;
            case ("Rook"):
                if (alliance.equals("white")) acronym = "wR";
                else acronym = "bR";
                break;
            default:
                break;
        }
        return acronym;
    }

    public void printBoard() {
        System.out.println("     a  b  c  d  e  f  g  h");
        System.out.println("  ###########################");
        for (int i=0; i<=7; i++) { //rows
            System.out.print(8-i+" #|"); //all rows begin with a divider
            for (int j=0; j<=7; j++) { //columns
                //print divider
                if (this.getTile(i,j).getPiece() == null) { //if empty square
                    System.out.print("  "); //print empty string
                } else {
                    System.out.print(convertPiece(this.getTile(i,j).getPiece().getName(), this.getTile(i,j).getPiece().getAlliance()));
                }
                System.out.print("|"); //print divider
            }
            System.out.println("# " +(8-i)); //next row
            if (i!=7) {
                System.out.println("  #|***********************|#");
            }
        }
        System.out.println("  ###########################");
        System.out.println();
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

    public void setP1(Player player) {
        this.p1 = player;
    }

    public void setP2(Player player) {
        this.p2 = player;
    }
    /** Get player one. */
    public Player getP1() { return p1; }

    /** Get player two. */
    public Player getP2() { return p2; }

    /** Get the opponent player. */
    public Player getOpponent(Player player) {
        if (player.getPlayerNum() == 1) {
            System.out.println("player num = 1");
            return getP2();
        }
        else return getP1();
    }

    /** Adds an attacked piece to the list of dead pieces. */
    public void addDeadPiece(Piece piece) {
        deadPieces.add(piece);
    }

}
