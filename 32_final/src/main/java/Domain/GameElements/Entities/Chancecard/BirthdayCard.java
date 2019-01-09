package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;

public class BirthdayCard {

    private int amount;

    /**
     * Counts together birthday money for the current player to accept amount based on number of players
     * @param player
     * @param players
     */
    @override
    public void action(Player player, Player[] players){
        for (Player p : players){
            p.getAccount().changeScore(-amount);
        }

        player.getAccount().changeScore(amount * players.length);
    }
}
