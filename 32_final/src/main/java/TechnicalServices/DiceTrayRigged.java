package TechnicalServices;

import Domain.GameElements.Entities.DiceTray;

public class DiceTrayRigged extends DiceTray {

    private int roundNr;


    private int[][] dieValues = {
            {1,3},
            {1,1},
            {1,4},
            {2,3},
            {2,4},
            {4,1},
            {4,2}
    };

    @Override
    public void Roll(){
        value1 = dieValues[roundNr][0];
        value2 = dieValues[roundNr][1];

        roundNr = ++roundNr % dieValues.length;
    }


}