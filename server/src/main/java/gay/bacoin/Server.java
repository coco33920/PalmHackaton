package gay.bacoin;

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
    }
}
