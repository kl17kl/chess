package board;

import pieces.*;
import java.util.*;

public class Board {

    public int rows = 8, cols = 8;
    private Tile[][] boardTiles;
    private List<Piece> activePieces;
    private String whiteSide; // either top/bot side of the board
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    public Board() {
    }

    /** Creates the board in its initial state. */
    private void createBoard() {
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                int[] coords = new int[]{i,j};
                boardTiles[i][j] = new EmptyTile(coords);
            }
        }
        setUpBoardPieces();
        activePieces = new LinkedList<Piece>();
    }

    /** Gets the tile at a given position. */
    public Tile getTile(int row, int col) {
        return this.boardTiles[row][col];
    }

    /** Returns the entire list of tiles. */
    public Tile[][] getBoardTiles() {
        return this.boardTiles;
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
                // add pawn to list of white/black and active pieces
                isWhite = alliance.equals("white") ? whitePieces.add(aPawn) : blackPieces.add(aPawn);
                activePieces.add(aPawn);
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
                        activePieces.add(aRook);
                        break;
                    case (1):
                    case (6):
                        Knight aKnight = new Knight(coords, alliance, this);
                        aTile.setPiece(aKnight);
                        isWhite = alliance.equals("white") ? whitePieces.add(aKnight) : blackPieces.add(aKnight);
                        activePieces.add(aKnight);
                        break;
                    case (2):
                    case (5):
                        Bishop aBishop = new Bishop(coords, alliance, this);
                        aTile.setPiece(aBishop);
                        isWhite = alliance.equals("white") ? whitePieces.add(aBishop) : blackPieces.add(aBishop);
                        activePieces.add(aBishop);
                        break;
                    case (3):
                        Queen aQueen = new Queen(coords, alliance, this);
                        aTile.setPiece(aQueen);
                        isWhite = alliance.equals("white") ? whitePieces.add(aQueen) : blackPieces.add(aQueen);
                        activePieces.add(aQueen);
                        break;
                    case (4):
                        King aKing = new King(coords, alliance, this);
                        aTile.setPiece(aKing);
                        isWhite = alliance.equals("white") ? whitePieces.add(aKing) : blackPieces.add(aKing);
                        activePieces.add(aKing);
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

    /** Gets the list of all the active pieces on the board. */
    public List<Piece> getActivePieces() {
        return activePieces;
    }

    /** Removes the attacked piece from the board (the list of active pieces). */
    public void removePiece(Piece piece) {
        activePieces.remove(piece);
    }

    /** Adds a new piece to the board (i.e. during promotion). */
    public void addPiece(Piece piece) {
        activePieces.add(piece);
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
