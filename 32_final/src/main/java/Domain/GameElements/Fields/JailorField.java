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
/*
 * If the player has a "get out of jail free" card, this removes the card in a method in players. Then next player.
 */
        if(player.getJailCards() >= 1){

            player.removeJailCards();

        }else{
            player.setPos(10);
            player.setJailTime(0);
        }


    }
}
