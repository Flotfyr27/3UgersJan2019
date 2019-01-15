package Domain.GameElements.Entities.Chancecard;
import Domain.Controller.PawnController;
import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;

public class TransactionCard extends ChanceCard {

    protected int amount;

    /**
     * Constructor.
     * @param amount
     */
    public TransactionCard (int amount, String description){
        super(description);
        this.amount = amount;
    }

    /**
     * Determines that 'action' is used to change the balance of players
     * @param p
     */
    @Override
    public void action (Player p){
        try{
        p.getAccount().changeScore(amount);
        }catch(RuntimeException e){
            String choice = GuiHandler.getInstance().makeButtons("Vil du pante eller give op?", "Pante", "Give op");
            if(choice.equalsIgnoreCase("Pante")){
                do {
                    PawnController.getInstance().runCase(p);
                }while(p.getAccount().getScore()+amount < 0);
            }
            else{
                p.setLost(true);
                p.setIsActive(false);
            }
        }
    }

}
