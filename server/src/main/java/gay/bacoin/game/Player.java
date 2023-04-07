package gay.bacoin.game;

import java.util.UUID;

public class Player {
    private final String name;
    private final String hexColor;
    private final String uuid;
    private int posX;
    private int posY;

    public Player(int posX, int posy, String name, String hexColor) {
        this.posX = posX;
        this.posY = posy;
        this.name = name;
        this.hexColor = hexColor;
        this.uuid = UUID.randomUUID().toString();
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

    public String getUuid() {
        return uuid;
    }
}
