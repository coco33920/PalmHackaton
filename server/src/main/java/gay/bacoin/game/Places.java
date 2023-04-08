package gay.bacoin.game;

public enum Places {
    SOIF(20, "Rue de la Soif"),
    AMPHIP(21, "Amphi P"),
    TROUNOIR(22, "Trou noir"),
    FOY(23, "Foyyer de la palme"),
    I59(24, "Salle I59"),
    TEMPLEOS(25, "Temple OS"),
    E303(26, "Salle E303"),
    GREVESLAND(27, "Rennes2");
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
