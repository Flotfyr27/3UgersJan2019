package Domain.GameElements.Fields.ChanceField;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Field;

import java.awt.*;

public class ChanceField extends Field {
    /**
     * ChanceField constructor
     * @param name Name of the field
     * @param subtext Subtext for the field
     * @param bgColour Colour of the field
     */
    public ChanceField(String name, String subtext, Color bgColour){
        super(name, subtext, bgColour);
    }


    @Override
    public void landOnAction(Player current) {

    }
}
