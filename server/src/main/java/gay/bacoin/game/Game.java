package gay.bacoin.game;

import gay.bacoin.game.tiles.BasicTile;
import gay.bacoin.game.tiles.Room;
import gay.bacoin.game.tiles.Tile;

import java.util.HashMap;
import java.util.UUID;

public class Game {
    private static HashMap<UUID, Game> allGameRunning = new HashMap<>();

    private Tile[][] map = new Tile[24][24];


    private void fillDefaultMap() {
        for (int i = 8; i < 16; i++) {
            map[0][i] = new BasicTile(i, 0);
        }

        map[1][1] = new Room(0, 1, 3, 9, "Rue de la Soif");
        for (int i = 9; i < 17; i++) {
            map[1][i] = new BasicTile(i, 1);
        }
        map[1][17] = new Room(0, 17, 5, 7, "TempleOS");

        for (int i = 9; i < 17; i++) {
            map[2][i] = new BasicTile(i, 2);
        }

        for (int i = 9; i < 17; i++) {
            map[3][i] = new BasicTile(i, 3);
        }

        for (int i = 1; i < 17; i++) {
            map[4][i] = new BasicTile(i, 4);
        }

        for (int i = 1; i < 17; i++) {
            map[5][i] = new BasicTile(i, 5);
        }

        for (int i = 1; i < 23; i++) {
            map[6][i] = new BasicTile(i, 6);
        }

        for (int i = 1; i < 24; i++) {
            map[7][i] = new BasicTile(i, 7);
        }

        map[8][0] = new Room(0,8,6,5,"Amphi P");
        for(int i = 5; i < 9; i++){
            map[8][i] = new BasicTile(i,8);
        }
        map[8][9] = new Room(9,8,7,6,"Palme");
        for(int i = 15; i < 19; i++){
            map[8][i] = new BasicTile(i,8);
        }
        map[8][19] = new Room(19,8,7,5, "E303");

        for(int i = 5; i < 9; i++){
            map[9][i] = new BasicTile(i,9);
        }
        for(int i = 15; i < 19; i++){
            map[9][i] = new BasicTile(i,9);
        }

        for(int i = 5; i < 9; i++){
            map[10][i] = new BasicTile(i,10);
        }
        for(int i = 15; i < 19; i++){
            map[10][i] = new BasicTile(i,10);
        }

        for(int i = 5; i < 9; i++){
            map[11][i] = new BasicTile(i,11);
        }
        for(int i = 15; i < 19; i++){
            map[11][i] = new BasicTile(i,11);
        }

        for(int i = 5; i < 9; i++){
            map[12][i] = new BasicTile(i,12);
        }
        for(int i = 15; i < 19; i++){
            map[12][i] = new BasicTile(i,12);
        }

        for(int i = 5; i < 9; i++){
            map[13][i] = new BasicTile(i,13);
        }
        for(int i = 15; i < 19; i++){
            map[13][i] = new BasicTile(i,13);
        }

        for(int i = 0; i < 9; i++){
            map[14][i] = new BasicTile(i,14);
        }
        for(int i = 15; i < 19; i++){
            map[14][i] = new BasicTile(i,14);
        }

        for(int i = 0; i < 24; i++){
            map[15][i] = new BasicTile(i,15);
        }

        for(int i = 1; i < 23; i++){
            map[16][i] = new BasicTile(i,16);
        }

        map[17][1] = new Room(1,17,6,5, "Trou Noir");
        for(int i = 7; i < 23; i++){
            map[17][1] = new BasicTile(i,17);
        }

        for(int i = 7; i < 18; i++){
            map[18][i] = new BasicTile(i,18);
        }
        map[18][18] = new Room(18,18,5,6,"Rennes 2");

        for(int i = 7; i < 18; i++){
            map[19][i] = new BasicTile(i,19);
        }

        map[20][7] = new BasicTile(7,20);
        map[20][8] = new BasicTile(8,20);
        map[20][9] = new Room(9,20,4,8,"Salle i59");
        map[20][17] = new BasicTile(17,20);

        map[21][7] = new BasicTile(7,21);
        map[21][8] = new BasicTile(8,21);
        map[21][17] = new BasicTile(17,21);

        map[22][7] = new BasicTile(7,21);
        map[22][8] = new BasicTile(8,21);
        map[22][17] = new BasicTile(17,21);

    }


    public Game() {
        fillDefaultMap();
    }


    public static void generateNewGame(){
        Game g = new Game();
        UUID uuid = UUID.randomUUID();
        addGame(uuid,g);
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
