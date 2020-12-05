package gui;

import java.util.*;
import pieces.*;
import player.*;
import board.*;

public class chessGame {

    public Player p1, p2;
    public String p1type, p2type, p1alliance, p2alliance;
    public Board board;
    public printBoard printboard;

    public chessGame() {
        // set-up player settings
        Scanner in = new Scanner(System.in);
        playerSettings(in);

        // confirm settings
        System.out.println("Continue with these settings?");
        System.out.println("Player 1: "+p1type+": "+p1alliance);
        System.out.println("Player 2: "+p2type+": "+p2alliance);
        System.out.println("1.Continue\t2.Quit");
        int proceed = in.nextInt();

        // player chooses to continue to game
        if (proceed == 1) {
            // print initial board
            board.createBoard();
            printboard = new printBoard(board);

            System.out.println("White player makes first move.\nEnter moves as [from coords] [to coords] (i.e. A4 A6)");
            // loop through the game
            while (!p1.isInCheckMate() && !p1.isInStaleMate() && !p2.isInCheckMate() && !p2.isInStaleMate()) {
                System.out.println("Player 1's turn.");
                takeTurn(p1, in);
                printboard = new printBoard(board);
                System.out.println("Player 2's turn.");
                takeTurn(p2, in);
                printboard = new printBoard(board);
            }
            // end of game
            if (p1.isInStaleMate() || p1.isInCheckMate()) System.out.println("Congratulations! Player 2 wins!");
            else System.out.println("Congratulations! Player 1 wins!");
        }
    }

    /**
     * What occurs during a player's turn. The system will take the user's input moves and will determine if input
     * is valid. If so, multiple checks are performed (e.g. is move legal, is player trying to promote a pawn, etc.).
     * The board gets updated with the moves and gets printed to the console.
     * @param p     the player (p1/p2)
     * @param in    the Scanner to read user input
     */
    private void takeTurn(Player p, Scanner in) {
        boolean input = false;
        // take user's input (move they wish to make)
        while (!input) {
            String move = in.nextLine();
            if (move.length() == 5 && isLetter(move.charAt(0)) && isLetter(move.charAt(3)) && isNum(move.charAt(1)) && isNum(move.charAt(4))) {
                input = true;
                int[] moveCoords = new int[]{Character.getNumericValue(move.charAt(0)),Character.getNumericValue(move.charAt(1)),Character.getNumericValue(move.charAt(3)),Character.getNumericValue(move.charAt(4))};
                // check if move is legal
                if (p.isMoveLegal(moveCoords, board)) {
                    if (board.getTile(moveCoords[0], moveCoords[1]).getPiece().getName().equals("Pawn")) {
                        // if player is performing a pawn jump (+2 tiles)
                        if (moveCoords[2] - moveCoords[0] == -2 || moveCoords[2] - moveCoords[0] == 2) {
                            board = p.doPawnJump(moveCoords, board);
                        }
                        // if player is promoting pawn
                        else if (moveCoords[2] == 7 || moveCoords[3] == 0) {
                            System.out.println("Pawn Promotion - Select piece to promote to:\n1.Queen\t2.Knight\t3.Rook\t4.Bishop");
                            while (true) {
                                int promo = in.nextInt();
                                if (1 <= promo && promo <= 4) {
                                    board = p.doPromotion(promo, moveCoords, board);
                                } else {
                                    System.out.println("Invalid choice - Select piece to promote to:\n1.Queen\t2.Knight\t3.Rook\t4.Bishop");
                                }
                            }
                        }
                        // if player is performing en-passant
                        else if (board.getTile(moveCoords[0], moveCoords[1]).getPiece().getFlag() && moveCoords[1] != moveCoords[3]) {
                            board = p.doEnPassant(moveCoords, board);
                        }
                        // reset all pawn's flags to false at end of turn
                        for (Piece piece : p.getActivePieces()) piece.setFlag(false);
                        break;
                    }
                    // for any other legal move
                    else {
                        board = p.makeMove(moveCoords, board);
                        break;
                    }
                }
            }
            // if user input is invalid
            else {
                input = false;
                System.out.println("Invalid move - Enter moves as [from coords] [to coords] (i.e. A4 A6)");
            }
        }
    }

    /**
     * Checks if the user's input at a specified character is valid (is a letter form A-H).
     * @param c the character to check
     * @return  true if is a character from A-H
     */
    private boolean isLetter(char c) {
        return c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F' || c == 'G' || c == 'H';
    }

    /**
     * Checks if the user's input at a specified character is valid (is a number form 1-8).
     * @param c the character to check
     * @return  true if is a character from 1-8
     */
    private boolean isNum(char c) {
        return c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8';
    }

    /**
     * Enables the user to input their game settings prior to starting an actual game.
     * The user can choose the types of players competing (player/computer) as well as the alliances
     * of each player (white/black).
     * @param in    the Scanner to read in user input
     */
    private void playerSettings(Scanner in) {
        System.out.println("___CHESS GAME___");
        System.out.println("PLAYER ONE:\nChoose player type.\n1.Player\t2.Computer");
        int onetype = in.nextInt();
        if (onetype ==1) p1type = "player";
        if (onetype ==2) p1type = "computer";

        System.out.println("Choose alliance.\n1.White\t2.Black");
        int onealliance = in.nextInt();
        if (onealliance == 1) p1alliance = "white";
        if (onealliance == 2) p1alliance = "black";

        System.out.println("PLAYER TWO:\nChoose player type.\n1.Player\t2.Computer");
        int twotype = in.nextInt();
        if (twotype == 1) p2type = "player";
        if (twotype == 2) p2type = "computer";

        System.out.println("Choose alliance.\n1.White\t2.Black");
        int twoalliance = in.nextInt();
        if (twoalliance == 1) p2alliance = "white";
        if (twoalliance == 2) p2alliance = "black";

        // assign player settings
        if (p1alliance.equals("white") && p1type.equals("player") && p2type.equals("player")) {
            board = new Board(p1,p2);
            List<Move> legalMoves = new LinkedList<>();
            p1 = new WhitePlayer("bot","player",board,legalMoves);
            p2 = new BlackPlayer("top","player",board,legalMoves);
        }
        if (p1alliance.equals("white") && p1type.equals("player") && p2type.equals("computer")) {
            board = new Board(p1,p2);
            List<Move> legalMoves = new LinkedList<>();
            p1 = new WhitePlayer("bot","player",board,legalMoves);
            p2 = new BlackPlayer("top","computer",board,legalMoves);
        }
        if (p1alliance.equals("white") && p1type.equals("computer") && p2type.equals("player")) {
            board = new Board(p1,p2);
            List<Move> legalMoves = new LinkedList<>();
            p1 = new WhitePlayer("top","computer",board,legalMoves);
            p2 = new BlackPlayer("bot","player",board,legalMoves);
        }
        if (p1alliance.equals("white") && p1type.equals("computer") && p2type.equals("computer")) {
            board = new Board(p1,p2);
            List<Move> legalMoves = new LinkedList<>();
            p1 = new WhitePlayer("top","computer",board,legalMoves);
            p2 = new BlackPlayer("bot","computer",board,legalMoves);
        }
    }
}
