package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;
import TechnicalServices.GameLogic.GameLogic;

public class MoveToJailChanceCard extends MoveToChanceCard {
    private GuiHandler guiHandler;

    /**
     * Constructor
     *
     * @param description The text displayed on the ChanceCard
     */
    public MoveToJailChanceCard(String description) {
        super(10, description);
        guiHandler = GuiHandler.getInstance();
    }

    /**
     * moves the player to jail and sets their jailTime to 0
     *
     * @param player The player who drew the card
     */
    @Override
    public void action(Player player) {

        //Checks for GetOutOfJailFree card before moving the player to jail
        if (player.getJailCards() >= 1) {
            guiHandler.giveMsg("Du bliver anholdt, men har kontkater til de rigtige mennesker, så politiet lader dig gå");
            player.removeJailCards();
        }
        else {
            player.setPos(super.value);
            player.setJailTime(0);

        }
    }

}