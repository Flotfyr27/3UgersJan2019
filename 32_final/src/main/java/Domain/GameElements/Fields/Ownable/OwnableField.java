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
    public void buyField(Player p){
        if(getOwner() == null){
            if(p.getAccount().getScore() >= getPrice()){
                setOwner(p);
                p.getAccount().changeScore(-getPrice());
                p.getOwnedFields().add(this);
            }
        }
    }
    /**
     * Method to determine what happens when a player lands on a field.
     * @param current The current player
     */
    @Override
    public void landOnAction(Player current) {
        if(getOwner() == null){
            buyField(current);
        }else if(getOwner() == current){
            return;
        }else{
            //TODO check to see if player has enough money to pay rent, else pawn!
            getOwner().getAccount().changeScore(getRent(current));
            current.getAccount().changeScore(-getRent(current));
        }

    }

    public abstract int getRent(Player player);

}
