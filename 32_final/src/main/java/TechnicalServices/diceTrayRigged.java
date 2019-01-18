package TechnicalServices;

import Domain.GameElements.Entities.diceTray;
import Domain.Controller.MoveController;

public class diceTrayRigged extends diceTray {

    private int roundNr;


    int[][] dieValues = {
            {1,2},
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
