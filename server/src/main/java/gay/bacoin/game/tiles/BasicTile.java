package gay.bacoin.game.tiles;

public class BasicTile extends Tile {
    private boolean occupied;

    public BasicTile(int x, int y) {
        super(x, y);
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "BasicTile [x=" + getX() + ";y=" + getY() + "]";
    }
}
