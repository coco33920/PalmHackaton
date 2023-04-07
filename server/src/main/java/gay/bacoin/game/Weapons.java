package gay.bacoin.game;

import java.util.ArrayList;

public class Weapons {

      public enum weapons {
          BEC(0, "bec benzène"),
          BROYEUR(1, "Broyeur"),
          CHALUMEAU(2, "Chalumeau"),
          RM(3, "rm -rf memoire.pdf"),
          GRIBOUILLAGE(4, "Gribouillage dessus"),
          CAFE(5, "Café"),
          WINDOWS(6, "Windows failure"),
          MARTEAU(7, "Marteau");
          ;

            private final String text;
            private final int id;

            /**
             * @param text
             */
            weapons(final int id, final String text) {
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
}
