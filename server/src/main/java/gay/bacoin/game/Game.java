package gay.bacoin.game;

import gay.bacoin.game.tiles.BasicTile;
import gay.bacoin.game.tiles.Room;
import gay.bacoin.game.tiles.Tile;
import gay.bacoin.json.CheckGuessRequest;
import gay.bacoin.json.MovePlayerRequest;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Game {
    private static final HashMap<UUID, Game> allGameRunning = new HashMap<>();

    private final Tile[][] map = new Tile[24][24];
    private final HashMap<Integer, Player> players = new HashMap<>();
    private final int[] solution = new int[3];


    public Game() {
        fillDefaultMap();
        generateDefaultPlayers();
        Random r = new Random();
        int weapon = r.nextInt(8);
        int places = r.nextInt(8);
        int player = r.nextInt(6);
        solution[0] = weapon;
        solution[1] = places;
        solution[2] = player;
    }

    public static UUID generateNewGame() {
        Game g = new Game();
        UUID uuid = UUID.randomUUID();
        addGame(uuid, g);
        return uuid;
    }

    public static void addGame(UUID uuid, Game game) {
        allGameRunning.put(uuid, game);
    }

    public static Game getGame(UUID uuid) {
        return allGameRunning.get(uuid);
    }

    private void fillDefaultMap() {
        //Line 1
        for (int i = 8; i < 16; i++) {
            map[0][i] = new BasicTile(i, 0);
        }

        //Line 2
        map[1][1] = new Room(0, 1, 3, 9, "Rue de la Soif");
        for (int i = 9; i < 17; i++) {
            map[1][i] = new BasicTile(i, 1);
        }
        map[1][17] = new Room(0, 17, 5, 7, "TempleOS");

        //Line 3
        for (int i = 9; i < 17; i++) {
            map[2][i] = new BasicTile(i, 2);
        }

        //Line 4
        for (int i = 9; i < 17; i++) {
            map[3][i] = new BasicTile(i, 3);
        }

        //Line 5
        for (int i = 1; i < 17; i++) {
            map[4][i] = new BasicTile(i, 4);
        }

        //Line 6
        for (int i = 1; i < 17; i++) {
            map[5][i] = new BasicTile(i, 5);
        }

        //Line 7
        for (int i = 1; i < 23; i++) {
            map[6][i] = new BasicTile(i, 6);
        }

        //Line 8
        for (int i = 0; i < 24; i++) {
            map[7][i] = new BasicTile(i, 7);
        }

        //Line 9
        map[8][0] = new Room(0, 8, 6, 5, "Amphi P");
        for (int i = 5; i < 9; i++) {
            map[8][i] = new BasicTile(i, 8);
        }
        map[8][9] = new Room(9, 8, 7, 6, "Palme");
        for (int i = 15; i < 19; i++) {
            map[8][i] = new BasicTile(i, 8);
        }
        map[8][19] = new Room(19, 8, 7, 5, "E303");

        //Line 10
        for (int i = 5; i < 9; i++) {
            map[9][i] = new BasicTile(i, 9);
        }
        for (int i = 15; i < 19; i++) {
            map[9][i] = new BasicTile(i, 9);
        }

        //Line 11
        for (int i = 5; i < 9; i++) {
            map[10][i] = new BasicTile(i, 10);
        }
        for (int i = 15; i < 19; i++) {
            map[10][i] = new BasicTile(i, 10);
        }

        //Line 12
        for (int i = 5; i < 9; i++) {
            map[11][i] = new BasicTile(i, 11);
        }
        for (int i = 15; i < 19; i++) {
            map[11][i] = new BasicTile(i, 11);
        }

        //Line 13
        for (int i = 5; i < 9; i++) {
            map[12][i] = new BasicTile(i, 12);
        }
        for (int i = 15; i < 19; i++) {
            map[12][i] = new BasicTile(i, 12);
        }

        //Line 14
        for (int i = 5; i < 9; i++) {
            map[13][i] = new BasicTile(i, 13);
        }
        for (int i = 15; i < 19; i++) {
            map[13][i] = new BasicTile(i, 13);
        }

        //Line 15
        for (int i = 0; i < 9; i++) {
            map[14][i] = new BasicTile(i, 14);
        }
        for (int i = 15; i < 19; i++) {
            map[14][i] = new BasicTile(i, 14);
        }

        //Line 16
        for (int i = 0; i < 24; i++) {
            map[15][i] = new BasicTile(i, 15);
        }

        //Line 17
        for (int i = 1; i < 23; i++) {
            map[16][i] = new BasicTile(i, 16);
        }

        //Line 18
        map[17][1] = new Room(1, 17, 6, 5, "Trou Noir");
        for (int i = 7; i < 23; i++) {
            map[17][1] = new BasicTile(i, 17);
        }

        //Line 19
        for (int i = 7; i < 18; i++) {
            map[18][i] = new BasicTile(i, 18);
        }
        map[18][18] = new Room(18, 18, 5, 6, "Rennes 2");

        //Line 20
        for (int i = 7; i < 18; i++) {
            map[19][i] = new BasicTile(i, 19);
        }

        //Line 21
        map[20][7] = new BasicTile(7, 20);
        map[20][8] = new BasicTile(8, 20);
        map[20][9] = new Room(9, 20, 4, 8, "Salle i59");
        map[20][17] = new BasicTile(17, 20);

        //Line 22
        map[21][7] = new BasicTile(7, 21);
        map[21][8] = new BasicTile(8, 21);
        map[21][17] = new BasicTile(17, 21);

        //Line 23
        map[22][7] = new BasicTile(7, 21);
        map[22][8] = new BasicTile(8, 21);
        map[22][17] = new BasicTile(17, 21);
    }

    private void generateDefaultPlayers() {
        Player player0 = new Player(0, 7, "Bacoin3301", "fc5e5e");
        players.put(0, player0);
        Player player1 = new Player(0, 15, "Un Chien", "e5e17b");
        players.put(1, player1);
        Player player2 = new Player(7, 22, "Olivier Ridoux", "e668f1");
        players.put(2, player2);
        Player player3 = new Player(16, 22, "The Doctor", "7b86e5");
        players.put(3, player3);
        Player player4 = new Player(23, 7, "La Palme", "86fa83");
        players.put(4, player4);
        Player player5 = new Player(23, 15, "OCaml", "7be5df");
        players.put(5, player5);
    }

    public boolean movePlayer(MovePlayerRequest request) {
        Player player = players.get(request.getUuid());

        Tile currentTile = getMap()[player.getPosY()][player.getPosX()];
        if (currentTile instanceof BasicTile) {
            BasicTile basicTile = (BasicTile) currentTile;
            basicTile.setOccupied(false);
        } else {
            Room room = (Room) currentTile;
            room.removePlayer(player);
        }

        if (request.getX() >= 24 || request.getY() >= 24 || request.getX() < 0 || request.getY() < 0) {
            return false;
        }

        Tile nextTile = getMap()[request.getY()][request.getX()];
        if (nextTile instanceof BasicTile) {
            BasicTile basicTile = (BasicTile) nextTile;
            if (basicTile.isOccupied()) return false;
            else {
                player.setPosX(request.getX());
                player.setPosY(request.getY());
                basicTile.setOccupied(true);
            }
        } else {
            Room room = (Room) nextTile;
            room.addPlayer(player);
            player.setPosX(request.getX());
            player.setPosY(request.getY());
        }
        return true;
    }

    public boolean checkGuess(CheckGuessRequest request) {
        return (solution[0] == request.getWeapon()) && (solution[1] == request.getPlace()) && (solution[2] == request.getKiller());
    }

    public Player[] getPlayerList() {
        return players.values().toArray(Player[]::new);
    }

    public Tile[][] getMap() {
        return map;
    }

    public int[] cardDistribution(){
        Weapons[] weapons = Weapons.values();

        Weapons.
    }
}
