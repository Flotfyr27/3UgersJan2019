package Domain.GameElements.Fields.Ownable;

import Domain.GameElements.Entities.Player;

import java.awt.*;

public class ShippingField extends OwnableField {
    /**
     * Constructor for the shippingfields
     * @param name Name of the field
     * @param subtext Subtext for the field
     * @param bgColour Background colour of the field
     * @param price Price of the field
     */
    public ShippingField(String name, String subtext, Color bgColour, int price){
        super(name, subtext, bgColour, price);
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
