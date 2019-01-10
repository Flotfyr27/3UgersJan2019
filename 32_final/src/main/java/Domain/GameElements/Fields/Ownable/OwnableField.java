package Domain.GameElements.Fields.Ownable;

import Domain.GameElements.Fields.Field;
import Domain.GameElements.Entities.Player;
import java.awt.*;

public abstract class OwnableField extends Field {
    private Player owner;
    private int price, rent;

    /**
     * Constructor for all ownable fields
     * @param name The name of the field
     * @param subtext The fields subtext
     * @param bgColour The background colour of the field
     * @param owner The owner of the field
     * @param price The price of the field
     * @param rent The rent of the field
     */
    public OwnableField(String name, String subtext, Color bgColour, Player owner, int price, int rent){
        super(name, subtext, bgColour);
        this.owner = owner;
        this.price = price;
        this.rent = rent;
    }

    /**
     * Returns the value of a field
     * @return The total value of a field
     */
    public int getWorth(){
        int worth = price;
        return worth;
    }

    /**
     * Method to get the price of a field
     * @return Integer value of the price of a field
     */
    public int getPrice(){
        return price;
    }
    public abstract void pawn();
    public abstract void payRent();
    public abstract void buy();
    public abstract void sell();

}
