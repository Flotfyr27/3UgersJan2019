package TechnicalServices;

import Domain.GameElements.Entities.DiceTray;

public class DiceTrayRigged extends DiceTray {

    private int roundNr;


    int[][] dieValues = {
            {2,3},
            {1,3},
            {1,4}
    };


    @Override
    public void roll(){
        value1 = dieValues[roundNr][0];
        value2 = dieValues[roundNr][1];

        roundNr = ++roundNr % dieValues.length;
    }


}