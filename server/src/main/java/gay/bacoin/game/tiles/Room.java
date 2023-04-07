package gay.bacoin.game.tiles;

import gay.bacoin.game.Player;

import java.util.ArrayList;

public class Room extends Tile {

    private ArrayList<Player> occupancy = new ArrayList<>();

    public Room(int x, int y) {
        super(x, y);
    }

    public void addPlayyer(Player player) {
        this.occupancy.add(player);
    }

    public void removePlayyer(Player player) {
        this.occupancy.remove(player);
    }

}
