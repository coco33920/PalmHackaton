package gay.bacoin.json;

public class MovePlayerRequest {

    private final String uuid;
    private final int x;
    private final int y;

    public MovePlayerRequest(String uuid, int x, int y) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
    }

    public String getUuid() {
        return uuid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
