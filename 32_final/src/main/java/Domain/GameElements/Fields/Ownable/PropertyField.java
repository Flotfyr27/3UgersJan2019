package Domain.GameElements.Fields.Ownable;

import java.awt.*;
import Domain.GameElements.Entities.Player;

public class PropertyField extends OwnableField{
    public PropertyField(String name, String subtext, Color bgColour, Player owner, int price, int rent, int housePrice){
        super(name, subtext, bgColour, owner, price, rent);
        this.housePrice = housePrice;
    }
    private int numberOfHouses = 0, housePrice;
    private int hotelPrice = housePrice*5;
    private boolean hasHotel = false;

    public int getHouses(){
        return numberOfHouses;
    }
    public boolean getHotel(){
        return hasHotel;
    }

    @Override
    public int getWorth() {
        int worth = super.getWorth()+(numberOfHouses*housePrice);
        if(hasHotel){
            worth += hotelPrice;
        }
        return worth;
    }

    @Override
    public void sell() {

    }

    @Override
    public void buy() {

    }

    @Override
    public void pawn() {

    }

    @Override
    public void payRent() {

    }
}
