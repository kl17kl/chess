package player;

import board.*;
import pieces.*;

import java.util.List;

public class WhitePlayer extends Player {

    private String alliance; //black
    private String boardSide; //top/bot
    private String type; //comp/human
    private Board board;
    private List<Piece> activePieces;

    public WhitePlayer(String alliance, String boardSide, String type, Board board) {
        super (alliance, boardSide, type, board);
        // generate the player's list of active pieces
        if (boardSide.equals("top")) {
            generatePieces(1,0);
        }
        else {
            generatePieces(6,7);
        }
    }

}