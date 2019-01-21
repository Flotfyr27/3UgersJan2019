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
     * @param value The amount the score should change with, positive or negative.
     */
    public void changeScore(int value) throws RuntimeException {
        score += value;
    }

    /**
     * A getter used to get the score from the account.
     *
     * @return The current score of the player
     */
    public int getScore() {
        return score;
    }


    //Setting the score for Presentation Mode
    public int setScore(int score){
        this.score = score;
        return score;
    }


    /**
     *  A method that checks if you are able to buy or not.
     * @param value The value being checked, always considered.
     * @return true if the account the player can pay amount and not go below 0
     */
    public boolean canBuy(int value) {
        if (score - Math.abs(value) < 0) {
            return false;
        } else {
            return true;
        }
    }
}