package Domain.GameElements.Fields.Ownable;

import java.awt.*;
import Domain.GameElements.Entities.Player;

public class PropertyField extends OwnableField{
    /**
     * Constructor for PropertyField
     * @param name Name of the field
     * @param subtext Subtext for the field
     * @param bgColour background colour for the field
     * @param price Price of the field
     * @param housePrice Price per house on the field
     */
    public PropertyField(String name, String subtext, Color bgColour, int price, int housePrice){
        super(name, subtext, bgColour, price);
        this.housePrice = housePrice;
    }
    private int numberOfHouses = 0, housePrice;
    private int hotelPrice = housePrice*5;
    private boolean hasHotel = false;

    /**
     * Method retrieves number of houses on the field
     * @return integer value of number of houses
     */
    public int getHouses(){
        return numberOfHouses;
    }

    /**
     * Method returns boolean value depending on the presence of a hotel
     * @return True/false if field has a hotel
     */
    public boolean getHotel(){
        return hasHotel;
    }

    /**
     * Method returns the total value of a property, which includes houses and hotels.
     * @return Integer value of the total property value.
     */
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

    @Override
    public void landOnAction(Player current) {

    }
}
