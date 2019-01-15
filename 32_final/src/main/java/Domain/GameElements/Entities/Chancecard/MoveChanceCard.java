package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.GameLogic;

public class MoveChanceCard extends ChanceCard {
    protected int value;

    /**
     * Constructor. The amount is the number of fields the player traverses.
     * The description is the text presented to the player.
     *
     * @param amount
     * @param description
     */
    public MoveChanceCard (int amount, String description){
        super(description);
        value = amount;
    }

    /**
     * This method moves a player by a specific amount and makes sure you get money if you pass the start field.
     *
     * @param player
     */
    @Override
    public void action(Player player){
        int destination;

        if ((player.getPos() + value) < 0 )
            destination = 40 + (player.getPos() + value);
        else
            destination = (player.getPos() + value)%40;

        if (value >= 0){
            GameLogic.movingPastStart(player, destination);
            player.setPos(destination);
        } else {
            player.setPos(destination);
        }
    }

}
