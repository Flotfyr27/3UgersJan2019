package main.java.Domain.GameElements.Entities;

public class Account {
    private int score;

    public Account() {
        score = 0;

    }

    public void changeScore(int value) {
        score += value;
    }

    public int getScore() {
        return score;
    }
}