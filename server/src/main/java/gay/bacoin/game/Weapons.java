package gay.bacoin.game;

public enum Weapons {
    BEC(10, "bec benzène"),
    BROYEUR(11, "Broyeur"),
    CHALUMEAU(12, "Chalumeau"),
    RM(13, "rm -rf memoire.pdf"),
    GRIBOUILLAGE(14, "Gribouillage dessus"),
    CAFE(15, "Café"),
    WINDOWS(16, "Windows failure"),
    MARTEAU(17, "Marteau");
    ;

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