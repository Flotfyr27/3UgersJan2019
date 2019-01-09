package Domain.GameElements.Fields.Ownable;

import Domain.GameElements.Fields.Field;
import main.java.Domain.GameElements.Entities.Player;

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
    public void pawn(){

    }

    public void payRent(){

    }


    public abstract void buy();
    public abstract void sell();

}
