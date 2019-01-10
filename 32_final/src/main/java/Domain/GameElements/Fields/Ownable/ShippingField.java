package Domain.GameElements.Fields.Ownable;

import java.awt.*;
import Domain.GameElements.Entities.Player;

public class ShippingField extends OwnableField {
    public ShippingField(String name, String subtext, Color bgColour, Player owner, int price, int rent){
        super(name, subtext, bgColour, owner, price, rent);
    }

    @Override
    public void buy() {

    }

    @Override
    public void sell() {

    }

    @Override
    public void pawn() {

    }

    @Override
    public void payRent() {

    }
}
