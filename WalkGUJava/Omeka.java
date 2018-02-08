package harvey.com.walkgujava;

/**
 * Created by Danielle on 2/4/2018.
 */

public class Omeka {
            // table row id
        private int id;
        private String name;


        public Omeka() {
            id = -1; // determined later by database table insertion order
            name = "";

        }

        public Omeka(String name) {
            this();
            this.name = name;

        }

        public Omeka(int id, String name) {
            this.id = id;
            this.name = name;

        }

        @Override
        public String toString() {
            return id + " " + name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


}
