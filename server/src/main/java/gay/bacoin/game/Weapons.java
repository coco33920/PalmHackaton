package gay.bacoin.game;

public enum Weapons {
    BEC(10, "bec benzène"),
    BROYEUR(11, "Broyeur"),
    RM(12, "rm -rf memoire.pdf"),
    GRIBOUILLAGE(13, "Gribouillage dessus"),
    CAFE(14, "Café"),
    WINDOWS(15, "Windows failure"),
    MARTEAU(16, "Marteau"),
    KATANA(17,"Katana");


    private final String text;
    private final int id;

    /**
     * @param text
     */
    Weapons(final int id, final String text) {
        this.id = id;
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

    public int getId() {
        return id;
    }
}