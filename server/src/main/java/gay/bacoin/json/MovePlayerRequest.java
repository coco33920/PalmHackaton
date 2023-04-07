package gay.bacoin.json;

public class MovePlayerRequest {

    private final int id;
    private final int x;
    private final int y;

    public MovePlayerRequest(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getUuid() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
