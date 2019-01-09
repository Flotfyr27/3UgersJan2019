package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Entities.Player;

public class TransactionCard extends ChanceCard {

    private int amount;

    private void action (Player p){
        p.getAccount().changeScore(amount);
    }



}
