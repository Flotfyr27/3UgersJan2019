package Domain.GameElements.Fields.Ownable;

import Domain.GameElements.Fields.Field;

import java.awt.*;

public abstract class OwnableField extends Field {
    private Player owner;
    private int price, rent;

    public OwnableField(String name, String subtext, Color bgColour, Player owner, int price, int rent){
        super(name, subtext, bgColour);
        this.owner = owner;
        this.price = price;
        this.rent = rent;
    }
    public abstract void pawn();
    public abstract void payRent();
    public abstract void buy();
    public abstract void sell();

}
