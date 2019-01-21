package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.GameLogic;


public class TransactionCard extends ChanceCard {

    protected int amount;

    /**
     * Constructor.
     *
     * @param amount The amount the player gets/looses
     */
    public TransactionCard (int amount, String description){
        super(description);
        this.amount = amount;
    }

    /**
     * Determines that 'action' is used to change the balance of players
     *
     * @param p The player drawing the card
     */
    @Override
    public void action (Player p){
        try{
        p.getAccount().changeScore(amount);
        }catch(RuntimeException e){
            GameLogic.cantPay(p, amount);
        }
    }

}
