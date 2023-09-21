package page;

import java.util.Random;

public class Geteri {

    public static String getRandomText() {
        Random random = new Random();
        return "Moroz " + random.nextInt(10000);
    }
}
