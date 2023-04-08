package gay.bacoin.game;

public class Player {
    private final String name;
    private final String hexColor;
    private int posX;
    private int posY;
    private int[] deck;

    public Player(int posX, int posy, String name, String hexColor) {
        this.posX = posX;
        this.posY = posy;
        this.name = name;
        this.hexColor = hexColor;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getName() {
        return name;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setDeck(int[] deck) {
        this.deck = deck;
    }
}
