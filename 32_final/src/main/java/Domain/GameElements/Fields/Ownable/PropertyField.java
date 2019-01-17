package Domain.GameElements.Fields.Ownable;

import java.awt.*;

import Domain.Controller.AuctionController;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.Values;
import UI.GUI.GuiHandler;

public class PropertyField extends OwnableField {

    private int numberOfHouses = 0, housePrice;
    private boolean hasHotel = false;
    private static int housesInPlay;
    private static int hotelsInPlay;

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


    /**
     * Method retrieves number of houses on the field
     * @return integer value of number of houses
     */
    public int getHouses() {
        return numberOfHouses;
    }

    public boolean getHotel(){
        if (numberOfHouses == 5) {
            return true;
        }
        return false;
    }

    /**
     * Method returns the total value of a property, which includes houses and hotels.
     * @return Integer value of the total property value.
     */
    @Override
    public int getWorth() {
        int worth = super.getWorth() + (numberOfHouses * housePrice);
        if (hasHotel) {
            worth += housePrice * 5;
        }
        return worth;
    }

    /**
     * Method to add a house, if 4 houses already exists a hotel will be added and houses removed.
     */
    public void addHouse() {
        if (numberOfHouses <= 4) {
            numberOfHouses++;
            housesInPlay++;
        } else if (numberOfHouses == 5) {
            numberOfHouses++;
            housesInPlay -= 4;
            hotelsInPlay++;
        } else {
            throw new RuntimeException("Maximum amount of houses already reached.");
        }
    }

    /**
     * Removes a house or hotel
     */
    public void removeHouse() {
        if (numberOfHouses >= 0 && numberOfHouses <= 4) {
            numberOfHouses--;
            housesInPlay--;
        } else if (numberOfHouses == 5) {
            numberOfHouses -= 5;
            hotelsInPlay--;
        } else {
            throw new RuntimeException("Minimum amount of houses already reached.");
        }
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

    public static int getHousesInPlay() {
        return housesInPlay;
    }

    public static int getHotelsInPlay() {
        return hotelsInPlay;
    }
}
