package Domain.GameElements.Entities;

public class Account {
    private int score;

    /**
     * Sets account balance to 30.000 which is the starting score.
     */
    public Account() {
        score = 30;
    }

    /**
     * Method to change the score and checks if you have enough money to buy.
     *
     * @param value
     */
    public void changeScore(int value) throws RuntimeException {
        if (canBuy(value)) {
            score += value;
        } else
            throw new RuntimeException("Error: Score would drop below zero");

    }

    /**
     * A getter used to get the score from the account.
     *
     * @return
     */
    public int getScore() {
        return score;
    }


    /**
     * A method that checks if you are able to buy or not.
     */
    public boolean canBuy(int value) {
        if (score+value < 0) {
            return false;
        } else {
            return true;
        }
    }
}