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

    }


    protected void passStartHandler(Player p, int destination){
        if (p.getPos() > destination)
            p.getAccount().changeScore();
    }
}
