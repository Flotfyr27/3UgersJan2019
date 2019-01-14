package Domain.GameElements.Fields;

import Domain.GameElements.Entities.Player;

import java.awt.*;

public class JailorField extends Field {
    /**
     *
     * @param name
     * @param subtext
     * @param bgColour
     */
    public JailorField(String name, String subtext, Color bgColour){
        super (name, subtext, bgColour);
    }

    /**
     * Moves the player to jail, and sets "Rounds in jail" to 0
     * @param player
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
        }


    }
}
