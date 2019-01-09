package Domain.GameElements.Fields.Ownable;

import main.java.Domain.GameElements.Entities.Player;

import java.awt.*;

public class ShippingField extends Domain.GameElements.Fields.Ownable.OwnableField {
    public ShippingField(String name, String subtext, Color bgColour, Player owner, int price, int rent){
        super(name, subtext, bgColour, owner, price, rent);
    }

    @Override
    public void buy() {

    }

    @Override
    public void sell() {

    }
}
