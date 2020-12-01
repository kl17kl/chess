package pieces;

import board.*;
import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece {
    private int[] position; // the piece's current position
    private String alliance; // white or black
    private Board board;
    private List<int[]> legalMoves;
    private String name;

    public Knight(int[] position, String alliance, Board board) {
        super(position, alliance, board);
        this.name = "Knight";
    }

    /**
     * Generates a list of the piece's legal moves given its current position.
     * @return the integer array list of legal moves
     */
    public List<int[]> legalMoves() {
        // stores all legal moves
        this.legalMoves = new LinkedList<>();
        int row = this.position[0];
        int col = this.position[1];

        // check each pair of jump destinations
        checkJumps(row, col, -2, 0); // north jumps
        checkJumps(row, col, 0, 2);  // east jumps
        checkJumps(row, col, 2, 0);  // south jumps
        checkJumps(row, col, 0, -2); // west jumps

        return this.legalMoves;
    }

    /**
     * Checks the possible jump destinations in a certain direction to see if a legal move can be made.
     * @param row           the piece's row coordinate
     * @param col           the piece's col coordinate
     * @param rowLength  the vertical direction to jump towards (up/down)
     * @param colLength  the horizontal direction to jump towards (right/left)
     */
    private void checkJumps(int row, int col, int rowLength, int colLength) {
        // if vertical jump..
        if (colLength == 0) {
            // if row is within the bounds of the chess board
            if (0 <= row+rowLength && row+rowLength <= board.rows) {
                // if col is within bounds and is not occupied with the same alliance
                if (0 <= col-1 && !this.board.getTile(row+rowLength,col-1).getPiece().getAlliance().equals(this.alliance)) {
                    // cannot take the opponent's King piece
                    if (!this.board.getTile(row+rowLength,col-1).getPiece().getName().equals("King")) {
                        this.legalMoves.add(this.board.getTile(row + rowLength, col - 1).getCoords());
                    }
                }
                if (col+1 <= board.cols && !this.board.getTile(row+rowLength,col+1).getPiece().getAlliance().equals(this.alliance)) {
                    // cannot take the opponent's King piece
                    if (!this.board.getTile(row+rowLength,col+1).getPiece().getName().equals("King")) {
                        this.legalMoves.add(this.board.getTile(row + rowLength, col+1).getCoords());
                    }
                }
            }
        }
        // if horizontal jump..
        else {
            // if col is within the bounds of the chess board
            if (0 <= col+colLength && col+colLength <= board.cols) {
                // if row is within bounds and is not occupied with the same alliance
                if (0 <= row-1 && !this.board.getTile(row-1,col+colLength).getPiece().getAlliance().equals(this.alliance)) {
                    // cannot take the opponent's King piece
                    if (!this.board.getTile(row-1,col+colLength).getPiece().getName().equals("King")) {
                        this.legalMoves.add(this.board.getTile(row-1, col+colLength).getCoords());
                    }
                }
                if (row+1 <= board.cols && !this.board.getTile(row+1,col+colLength).getPiece().getAlliance().equals(this.alliance)) {
                    // cannot take the opponent's King piece
                    if (!this.board.getTile(row+1,col+colLength).getPiece().getName().equals("King")) {
                        this.legalMoves.add(this.board.getTile(row+1, col+colLength).getCoords());
                    }
                }
            }
        }

    }

}
