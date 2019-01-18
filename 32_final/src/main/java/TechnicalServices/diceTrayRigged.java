package TechnicalServices;

import Domain.GameElements.Entities.DiceTray;

public class DiceTrayRigged extends DiceTray {

    private int value1;
    private int value2;
    private int sum;


    public void roll(int value1, int value2){
        this.value1 = value1;
        this.value2 = value2;
    }
    @Override
    public int getSum() {
        sum = this.value1 + this.value2;
        return sum;
    }

    public

}
