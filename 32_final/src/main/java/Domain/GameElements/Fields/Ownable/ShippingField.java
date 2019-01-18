package Domain.GameElements.Fields.Ownable;

import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.Values;

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

    /**
     * This method calculates what the rent is for the shipping fields.
     * @param p This is the player who lands on the field
     * @return Returns an integer value which is the rent.
     */
    @Override
    public int getRent(Player p) {
        int count = -1;
        for(int n = 0; n < getOwner().getOwnedFields().size(); n++){
            if(getOwner().getOwnedFields().get(n).getClass().equals(ShippingField.class)){
                count++;
            }
        }
        int rent = Values.rentPrice(p.getPos(), count);
        return rent;
    }
}
