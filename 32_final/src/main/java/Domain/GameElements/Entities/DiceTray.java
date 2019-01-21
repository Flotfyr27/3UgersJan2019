package Domain.GameElements.Entities;

public class DiceTray {
    private int value1;
    private int value2;
    private Die die;

    /**
     * Constructor for the diceTray
     */
    public DiceTray() {
        die = new Die();
    }

    /**
     * A Roll method. To get two values from the dice.
     */
    public void Roll() {
        value1 = die.Roll();
        value2 = die.Roll();
    }

    /**
     * A getSum method, to get the sum of the roll.
     * @return Returns the sum.
     */
    public int getSum() {
        return value1 + value2;
    }

    /**
     * Checks if the roll is double or or not.
     * @return It returns true if the roll was a double value
     */
    public boolean IsDoubleValue() {
        if (value1 == value2)
            return true;
        else
            return false;
    }

    /**
     * returns the value of the first die
     * @return value1
     */
    public int getValue1() {
        return value1;
    }

    /**
     * returns the value of the second die
     * @return value2
     */
    public int getValue2() {
        return value2;
    }
}










