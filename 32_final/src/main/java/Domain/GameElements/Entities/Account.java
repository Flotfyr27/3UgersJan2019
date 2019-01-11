package Domain.GameElements.Entities;

public class Account {
    private int score;

    /**
     * Sets account balance to 30.000 which is the starting score.
     */
    public Account() {
        score = 30000;
    }

    /**
     * Method to change the score.
     * @param value
     */
    public void changeScore(int value) {
        score += value;
    }

    /**
     * A getter used to get the score from the account.
     * @return
     */
    public int getScore() {
        return score;
    }
}