package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.GameLogic;

public class MoveChanceCard extends ChanceCard {
    protected int value;

    /**
     * TODO write javadoc
     * @param amount
     * @param description
     */
    public MoveChanceCard (int amount, String description){
        value = amount;
        super.description = description;
    }

    /**
     * TODO write javadoc
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
