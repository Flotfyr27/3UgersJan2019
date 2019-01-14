package Domain.GameElements.Fields.Ownable;

import Domain.GameElements.Fields.Field;
import Domain.GameElements.Entities.Player;
import java.awt.*;

public abstract class OwnableField extends Field {
    private Player owner;
    protected int price, rent;

    /**
     * Constructor for all ownable fields
     * @param name The name of the field
     * @param subtext The fields subtext
     * @param bgColour The background colour of the field
     * @param price The price of the field
     */
    public OwnableField(String name, String subtext, Color bgColour, int price){
        super(name, subtext, bgColour);
        this.price = price;
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

    /**
     * Method set the owner of a field.
     * @param player Player to own the field
     */
    public void setOwner(Player player){
        owner = player;
    }

    /**
     * Method to get the owner
     * @return Player who is the owner
     */
    public Player getOwner(){
        return owner;
    }
    public abstract void pawn();
    public abstract void payRent();
    public abstract void buy();
    public abstract void sell();

}
