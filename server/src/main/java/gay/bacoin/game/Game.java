package gay.bacoin.game;

import gay.bacoin.game.tiles.BasicTile;
import gay.bacoin.game.tiles.Tile;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class Game {
    private static HashMap<UUID, Game> allGameRunning = new HashMap<>();

    private Tile[][] map = new Tile[24][24];


    private void fillDefaultMap(){
        for(int i = 8; i < 16; i++){
            map[0][i] = new BasicTile(i,0);
        }

    }


    public Game(){


    }



    public static void addGame(UUID uuid, Game game) {
        allGameRunning.put(uuid, game);
    }

    public static Game getGame(UUID uuid) {
        Game g = allGameRunning.get(uuid);
        if (g == null)
            throw new RuntimeException("Error while accessing game");
        return g;
    }
}
