package Domain.GameElements.Entities;

public class DiceTray {
    private int value1;
    private int value2;
    private int sum;
    Die die;

    /**
     * Constructor for the dicetray
     */
    public DiceTray() {
        die = new Die();
    }

    /**
     * A Roll method. To get to get two values from the dice.
     */
    public void Roll() {
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










