package Domain.GameElements.Entities;

public class diceTray {
    protected int value1;
    protected int value2;
    protected int sum;
    Die die;

    /**
     * Constructor for the dicetray
     */
    public diceTray() {
        die = new Die();
    }

    /**
     * A roll method. To get to get two values from the dice.
     */
    public void roll() {
        value1 = die.Roll();
        value2 = die.Roll();
    }

    /**
     * A getSum method, to get the sum of the roll.
     * @return It returns the sum.
     */
    public int getSum() {
        sum = value1 + value2;
        return sum;
    }

    /**
     * Checks if the roll is double or or not.
     * @return It's return true or false.
     */
    public boolean IsDoubleValue() {
        if (value1 == value2)
            return true;
        else
            return false;
    }

    /**
     * returns the value of the first die
     * @return value
     */
    public int getValue1() {
        return value1;
    }

    /**
     * returns the value of the second die
     * @return value
     */
    public int getValue2() {
        return value2;
    }
}










