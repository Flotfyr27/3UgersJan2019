package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;

public class BirthdayCard extends TransactionCard {

    private Player[] players;

    /**
     * Constructor.
     * @param players
     */
    public BirthdayCard(int amount, Player[] players, String description){
        super(amount, description);
        this.players = players;
    }

    /**
     * takes a specific amount of money from every player and gives it to the player who drew the card.
     * @param player
     */
    @Override
    public void action(Player player){
        for (Player p : players){
            p.getAccount().changeScore(-super.amount);
        }

        //TODO make this method take into acount that some might not be able to pay

        player.getAccount().changeScore(super.amount * players.length);
    }
}
