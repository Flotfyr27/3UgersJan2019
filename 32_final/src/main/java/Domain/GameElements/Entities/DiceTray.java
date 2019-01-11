package Domain.GameElements.Entities;

public class DieTray {
    private int value1;
    private int value2;
    public int sum;
    Die die;

    public DieTray() {
        die = new Die();
        Roll();
    }

    /**
     * a Roll method. To get to get two values from the dice.
     */
    public void Roll() {
        value1 = die.Roll();
        value2 = die.Roll();
    }

    /**
     * a getSum method, to get the sum of the roll.
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


}










