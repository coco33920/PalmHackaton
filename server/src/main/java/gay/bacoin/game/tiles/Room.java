package gay.bacoin.game.tiles;

import gay.bacoin.game.Player;

import java.util.ArrayList;

public class Room extends Tile {

    private int height;
    private int width;

    private ArrayList<Player> occupancy = new ArrayList<>();

    public Room(int x, int y, int height, int width) {
        super(x, y);
        this.height = height;
        this.width = width;
    }

    public void addPlayyer(Player player) {
        this.occupancy.add(player);
    }

    public void removePlayyer(Player player) {
        this.occupancy.remove(player);
    }

}
