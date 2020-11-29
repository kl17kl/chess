package board;

import pieces.*;
import java.util.*;

public class Board {

    public int rows = 8, cols = 8;
    private Tile[][] boardTiles;
    private List<Piece> deadPieces; //the dead pieces
    private String whiteSide; // either top/bot side of the board
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    public Board() {
    }

    /** Creates the board in its initial state. */
    private void createBoard() {
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

    /** Set-up the chess board with its pieces */
    private void setUpBoardPieces() {
        // determine what side has what alliance
        boolean isWhite;
        String topAlliance = "";
        String botAlliance = "";
        if (whiteSide.equals("top")) {
            topAlliance = "white";
            botAlliance = "black";
        }
        else {
            topAlliance = "black";
            botAlliance = "white";
        }
        // initiate lists of white and black pieces
        whitePieces = new LinkedList<>();
        blackPieces = new LinkedList<>();
        // set up pawn pieces on both sides - starting at the second-top row
        int i = 1;
        String alliance = topAlliance;
        for (int k=0; k<2; k++) {
            for (int j = 0; j < 8; j++) {
                int[] coords = new int[]{i, j};
                OccupiedTile aTile = new OccupiedTile(coords);
                Pawn aPawn = new Pawn(coords, alliance, this);
                aTile.setPiece(aPawn);
                this.boardTiles[i][j] = aTile;
                // add pawn to list of white/black pieces
                isWhite = alliance.equals("white") ? whitePieces.add(aPawn) : blackPieces.add(aPawn);
            }
            // update row to 6 to fill in the bottom row of pawns
            i = 6;
            alliance = botAlliance;
        }
        // set up the remaining pieces
        i = 0;
        alliance = topAlliance;
        for (int k=0; k<2; k++) {
            for (int j = 0; j < cols; j++) {
                int[] coords = new int[]{i, j};
                OccupiedTile aTile = new OccupiedTile(coords);
                switch (j) {
                    case (0):
                    case (7):
                        Rook aRook = new Rook(coords, alliance, this);
                        aTile.setPiece(aRook);
                        isWhite = alliance.equals("white") ? whitePieces.add(aRook) : blackPieces.add(aRook);
                        break;
                    case (1):
                    case (6):
                        Knight aKnight = new Knight(coords, alliance, this);
                        aTile.setPiece(aKnight);
                        isWhite = alliance.equals("white") ? whitePieces.add(aKnight) : blackPieces.add(aKnight);
                        break;
                    case (2):
                    case (5):
                        Bishop aBishop = new Bishop(coords, alliance, this);
                        aTile.setPiece(aBishop);
                        isWhite = alliance.equals("white") ? whitePieces.add(aBishop) : blackPieces.add(aBishop);
                        break;
                    case (3):
                        Queen aQueen = new Queen(coords, alliance, this);
                        aTile.setPiece(aQueen);
                        isWhite = alliance.equals("white") ? whitePieces.add(aQueen) : blackPieces.add(aQueen);
                        break;
                    case (4):
                        King aKing = new King(coords, alliance, this);
                        aTile.setPiece(aKing);
                        isWhite = alliance.equals("white") ? whitePieces.add(aKing) : blackPieces.add(aKing);
                        break;
                    default:
                        break;
                }
                this.boardTiles[i][j] = aTile;
            }
            // update row to 7 to fill in the bottom pieces
            i=7;
            alliance = botAlliance;
        }
    }

    /** Gets the tile at a given position. */
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
                    if (p.getPosition().equals(coords)) {
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

    /** Adds an attacked piece to the list of dead pieces. */
    public void addDeadPiece(Piece piece) {
        deadPieces.add(piece);
    }

    /** Gets the list of all the white pieces on the board. */
    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    /** Gets the list of all the black pieces on the board. */
    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

}
