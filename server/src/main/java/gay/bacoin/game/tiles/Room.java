package gay.bacoin.game.tiles;

import gay.bacoin.game.Player;

import java.util.ArrayList;

public class Room extends Tile {

    private final int height;
    private final int width;
    private final String roomName;

    private final ArrayList<Player> occupancy = new ArrayList<>();

    public Room(int x, int y, int height, int width, String roomName) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.roomName = roomName;
    }

    public void addPlayer(Player player) {
        this.occupancy.add(player);
    }

    public void removePlayer(Player player) {
        this.occupancy.remove(player);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getRoomName() {
        return roomName;
    }
}
