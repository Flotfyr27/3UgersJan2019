package Domain.GameElements.Fields.Ownable;

import java.awt.*;

public class PropertyField extends OwnableField{

    public PropertyField(String name, String subtext, Color bgColour, Player owner, int price, int rent){
        super(name, subtext, bgColour, owner, price, rent);
    }

    @Override
    public void sell() {

    }

    @Override
    public void buy() {

    }
}
