package Domain.GameElements.Entities;

import java.util.Random;

public class Die {
    private Random random;
    private int dieFaces = 6;
    private int faceValue;

    public Die(){
        random = new Random();
    }

    public int Roll (){
        faceValue = Math.abs(random.nextInt(dieFaces)+1);
       return faceValue;
    }
}
