package Domain.GameElements.Fields;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;

import java.awt.*;

public class JailorField extends Field {
    /**
     * Constructor
     *
     * @param name The name of the field
     * @param subtext A short description of the fields effect.
     * @param bgColour The color of the field
     */
    public JailorField(String name, String subtext, Color bgColour){
        super (name, subtext, bgColour);
    }

    /**
     * Moves the player to jail, and sets "jailTime" to 0
     * @param player the player landing on the field
     */
    @Override
    public void landOnAction(Player player){

        if(player.getJailCards() >= 1){
            guiHandler.giveMsg("Du bliver anholdt, men har kontkater til de rigtige mennesker, så politiet lader dig gå");
            player.removeJailCards();

        }else{
            guiHandler.giveMsg("Du bliver anholdt, og bliver sendt i fængsel");
            player.setPos(10);
            player.setJailTime(0);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GuiHandler.getInstance().teleportPlayer(player, Board.getInstance().getPlayers());
        }
    }
}
