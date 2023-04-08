package gay.bacoin;

import com.google.gson.Gson;
import gay.bacoin.game.Game;
import gay.bacoin.json.CheckGuessRequest;
import gay.bacoin.json.MovePlayerRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        ipAddress("127.0.0.1");
        port(8080);
        setupRoute();
    }

    private static void setupRoute() {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });

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
            boolean success = g.movePlayer(movePlayerRequest);
            return "{\"success\":" + success + "}";
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

        get("/olivierridoudoux", (request, response) -> {
            response.type("image/png");
            BufferedImage ridoudoux = ImageIO.read(Server.class.getClassLoader().getResource("ridoudou.png"));
            byte[] rawImage = null;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(ridoudoux, "png", baos);
                baos.flush();
                rawImage = baos.toByteArray();
            }

            return rawImage;
        });
    }
}
