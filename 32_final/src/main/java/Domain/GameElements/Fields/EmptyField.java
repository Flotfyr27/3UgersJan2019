package Domain.GameElements.Fields;

import Domain.GameElements.Entities.Player;

import java.awt.*;

public class EmptyField extends Field {

    public EmptyField (String name, String subtext, Color bgColour){
        super(name, subtext, bgColour);
    }

    public void landOnAction(Player current) {

    }
}
