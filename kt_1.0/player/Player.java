package player;

public abstract class Player {

    private String alliance; //white/black
    private String boardSide; //top/bot
    private String type; //comp/human

    public Player(String alliance, String boardSide, String type) {
        this.alliance = alliance;
        this.boardSide = boardSide;
        this.type = type;
    }

    /** Get the alliance of the player (white/black) */
    public String getAlliance() {
        return this.alliance;
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
