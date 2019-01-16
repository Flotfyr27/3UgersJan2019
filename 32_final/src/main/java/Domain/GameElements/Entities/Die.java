package Domain.GameElements.Entities;

import java.util.Random;

public class Die {
    private Random random;
    private int dieFaces = 6;
    private int faceValue;

    /**
     * Constructor to make it random.
     */
    public Die(){
        random = new Random();
    }

    /**
     * Roll method allow us to roll us to roll the dice and makes it random with the math.abs() method
     * @return
     */
    public int Roll (){
        faceValue = Math.abs(random.nextInt(dieFaces)+1);
       return 1;//faceValue;
    }
}
