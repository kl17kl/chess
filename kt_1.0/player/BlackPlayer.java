package player;

public class BlackPlayer extends Player{

    private String alliance; //white/black
    private String boardSide; //top/bot
    private String type; //comp/human

    public BlackPlayer(String alliance, String boardSide, String type) {
        super(alliance, boardSide, type);
    }

    /** Get the alliance of the player (white/black) */
    public String getAlliance() {
        return "black";
    }

    /** Get the side of the board of the player (top/bot) */
    public String getBoardSide() {
        return this.boardSide;
    }

    /** Get the type of the player (comp/human) */
    public String getType() {
        return this.type;
    }
}
