package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;
import TechnicalServices.GameLogic.GameLogic;

public class MoveToJailChanceCard extends MoveToChanceCard {
    private GuiHandler guiHandler;

    public MoveToJailChanceCard(int destination, String description) {
        super(destination, description);
        guiHandler = GuiHandler.getInstance();
    }

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