package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;

public class BirthdayCard extends TransactionCard {

    private Player[] players;
    private GuiHandler guiHandler = GuiHandler.getInstance();

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
     * @param player the player who drew the card
     */
    @Override
    public void action(Player player){
        int count = 0;
        for (Player p : players){
            if (!p.getLost()) {
                boolean hasPaid = false;
                do {
                    if (p.getAccount().canBuy(-super.amount)) {
                        p.getAccount().changeScore(-super.amount);
                        count++;
                        hasPaid = true;
                    } else {

                        guiHandler.msgInMiddle(p.getName() + "kan ikke betale, pantsæt eller sælg en grund så du kan betale");
                        String str = guiHandler.makeButtons("Hvordan vil du skaffe pengene?", "Pantsæt", "Handel");
                        if (str.equalsIgnoreCase("Pantsæt")) {
                            //TODO insert pawnController call here
                        } else {
                            //TODO insert tradeController call here
                        }
                    }

                }while (!hasPaid);
            }
        }


        player.getAccount().changeScore(super.amount * count);
    }
}
