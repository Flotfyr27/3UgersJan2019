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
     * Method to change the score and checks if you have enough money to buy.
     *
     * @param value
     */
    public boolean changeScore(int value) {
        if (canBuy(value)) {
            score += value;
            return true;
        } else  {
            return false;
        }
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
    private boolean canBuy(int value) {
        if (score+value < 0) {
            return false;
        } else {
            return true;
        }
    }
}