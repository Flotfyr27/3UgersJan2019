package Domain.GameElements.Fields;

import Domain.GameElements.Entities.Player;

import java.awt.*;

public class EmptyField extends Field {

    /**
     * Constructor
     * @param name The name of the field
     * @param subtext A short description of the field
     * @param bgColour the color of the field
     */
    public EmptyField (String name, String subtext, Color bgColour){
        super(name, subtext, bgColour);
    }

    /**
     * An empty method signifying that the field does nothing when you land on it.
     * @param current The current player
     */
    @Override
    public void landOnAction(Player current) {

    }
}
