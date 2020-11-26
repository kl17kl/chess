package board;

public class Board {

    public int rows = 8, cols = 8;
    private Tile[][] boardTiles;

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
    }

    /** Gets the tile at a given position. */
    public Tile getTile(int row, int col) {
        return this.boardTiles[row][col];
    }

    /** Returns the entire list of tiles. */
    public Tile[][] getBoardTiles() {
        return this.boardTiles;
    }

    //has collection of white and black pieces
    //has list of active pieces
    //get active pieces()
    //setPieces()
}
