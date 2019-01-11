package Domain.GameElements.Fields.Ownable;

import java.awt.*;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.Values;

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
            worth += housePrice*5;
        }
        return worth;
    }

    /**
     * Method to add a house, if 4 houses already exists a hotel will be added and houses removed.
     */
    public void addHouse(){
        if(hasHotel){

        }else if(numberOfHouses < 4){
            numberOfHouses++;
        }else if(!hasHotel && numberOfHouses == 4){
            hasHotel = true;
            numberOfHouses = 0;
        }else {
            return;
        }
    }

    /**
     * Removes a house based on the numeric value given
     * @param value The amount of houses to remove
     */
    public void removeHouse(int value){
        numberOfHouses -= value;
    }

    /**
     * This method calculates the rent of a property
     * @param p The player who lands on the field
     * @return Returns an integer value of rent
     */
    @Override
    public int getRent(Player p) {
        int rent = Values.rentPrice(p.getPos(), numberOfHouses);
        return rent;
    }
}
