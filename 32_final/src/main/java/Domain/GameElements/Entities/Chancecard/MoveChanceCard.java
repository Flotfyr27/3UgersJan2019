package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.GameLogic;

public class MoveChanceCard extends ChanceCard {
    protected int value;

    public MoveChanceCard (int amount){
        value = amount;
    }


    @Override
    public void action(Player player){
        int destination = player.getPos() + value;

        if (value >= 0){
            GameLogic.movingPastStart(player, destination);
            player.setPos(destination);
        } else {
            player.setPos(destination);
        }
    }

}
