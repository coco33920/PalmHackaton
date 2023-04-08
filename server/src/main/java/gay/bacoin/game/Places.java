package gay.bacoin.game;

public enum Places {
    SOIF(0, "Rue de la Soif"),
    AMPHIP(1, "Amphi P"),
    TROUNOIR(2, "Trou noir"),
    FOY(3, "Foyyer de la palme"),
    I59(4, "Salle I59"),
    TEMPLEOS(5, "Temple OS"),
    E303(6, "Salle E303"),
    GREVESLAND(7, "Rennes2");
    ;

    private final String text;
    private final int id;

    /**
     * @param text
     */
    Places(final int id, final String text) {
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
