package Domain.GameElements.Fields.ChanceField;

import Domain.GameElements.Entities.ChanceCardStack;
import Domain.GameElements.Entities.Chancecard.ChanceCard;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Field;
import java.awt.*;

public class ChanceField extends Field {
    private ChanceCardStack stack;

    /**
     * ChanceField constructor
     * @param name Name of the field
     * @param subtext Subtext for the field
     * @param bgColour Colour of the field
     */
    public ChanceField(String name, String subtext, Color bgColour){
        super(name, subtext, bgColour);
        stack = ChanceCardStack.getStackInstance();
    }


    /**
     * Draws a chanceCard and activates its effect.
     * @param current The current player
     */
    @Override
    public void landOnAction(Player current) {
        guiHandler.msgInMiddle(stack.getCurrent().toString());
        guiHandler.giveMsg("Du trækker et Chancekort");

        stack.next().action(current);
    }
}
