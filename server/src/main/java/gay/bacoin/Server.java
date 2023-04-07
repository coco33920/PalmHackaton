package gay.bacoin;

import com.google.gson.Gson;
import gay.bacoin.game.Game;
import gay.bacoin.json.CheckGuessRequest;
import gay.bacoin.json.MovePlayerRequest;

import java.util.UUID;

import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        ipAddress("127.0.0.1");
        port(8080);
        setupRoute();
    }

    private static void setupRoute() {
        get("/", (request, response) -> {
            response.type("application/json");
            return "{\"text\":\"Hello World\"}";
        });
        get("/request_new_game", (request, response) -> {
            UUID uuid = Game.generateNewGame();
            response.type("application/json");
            return "{\"uuid\":\"" + uuid + "\"}";
        });
        get("/game_state/:uuid", (request, response) -> {
            response.type("application/json");
            String uuid = request.params(":uuid");
            UUID id = UUID.fromString(uuid);
            Game g = Game.getGame(id);
            if (g == null)
                return "{}";
            return new Gson().toJson(g.getPlayerList());
        });
        post("/move_player/:uuid", (request, response) -> {
            response.type("application/json");
            String uuid = request.params(":uuid");
            Game g = Game.getGame(UUID.fromString(uuid));
            if (g == null)
                return "{}";
            String body = request.body();
            MovePlayerRequest movePlayerRequest = new Gson().fromJson(body, MovePlayerRequest.class);
            g.movePlayer(movePlayerRequest);
            return "{\"status\":\"Ok\"}";
        });
        post("/check_guess/:uuid", (request, response) -> {
            response.type("application/json");
            String uuid = request.params(":uuid");
            Game g = Game.getGame(UUID.fromString(uuid));
            if (g == null)
                return "{}";
            String body = request.body();
            CheckGuessRequest checkGuessRequest = new Gson().fromJson(body, CheckGuessRequest.class);
            boolean success = g.checkGuess(checkGuessRequest);
            return "{\"success\":" + success + "}";
        });
    }
}
