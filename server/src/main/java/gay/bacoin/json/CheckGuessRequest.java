package gay.bacoin.json;

public class CheckGuessRequest {

    private final int weapon;
    private final int killer;
    private final int place;

    public CheckGuessRequest(int weapon, int killer, int place) {
        this.weapon = weapon;
        this.killer = killer;
        this.place = place;
    }

    public int getWeapon() {
        return weapon;
    }

    public int getKiller() {
        return killer;
    }

    public int getPlace() {
        return place;
    }
}
