package Domain.GameElements.Entities;

import java.util.Random;

public class Die {
    private Random random;

    /**
     * Constructor to make it random.
     */
    public Die(){
        random = new Random();
    }

    /**
     * The roll method utilizes the java.util.Random class to get a random number and constrain it to the number of faces on a die
     * @return A random integer between and including 1 and 6
     */
    public int Roll () {
        int dieFaces = 6;
        return Math.abs(random.nextInt(dieFaces)) + 1;
    }
}
