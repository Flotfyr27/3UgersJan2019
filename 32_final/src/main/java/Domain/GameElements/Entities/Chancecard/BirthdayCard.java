package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;

public class BirthdayCard extends TransactionCard {

    private int amount;
    private Player[] players;

    /**
     * Constructor.
     * @param players
     */
    public BirthdayCard(Player[] players){
        this.players = players;
    }

    /**
     * Counts together birthday money for the current player to accept amount based on number of players
     * @param player
     */
    @Override
    public void action(Player player){
        for (Player p : players){
            p.getAccount().changeScore(-amount);
        }

        player.getAccount().changeScore(amount * players.length);
    }
}
