package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Account;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;

public class MatadorLegateChanceCard extends TransactionCard {

    /**
     * Constructor.
     *
     * @param description
     */
    public MatadorLegateChanceCard(int amount, String description) {
        super(amount, description);
    }

    /**
     * gives the player a large sum of money if they currently own
     * less than 15000 worth of buildings and money
     * @param player
     */
    public void action(Player player) {
        int ownableWorth = 0;
        for (OwnableField field : player.getOwnedFields())
            ownableWorth += field.getWorth();

        if ((player.getAccount().getScore() + ownableWorth) < 15000) {
            super.action(player);
        }
    }

}
