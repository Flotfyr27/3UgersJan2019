package Domain.GameElements.Fields.Ownable;

import java.awt.*;

import Domain.Controller.AuctionController;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.Values;
import UI.GUI.GuiHandler;

public class PropertyField extends OwnableField {

    private int numberOfHouses = 0, housePrice;
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

    /**
     * checks if the field has a hotel
     * @return true if the field has a hotel
     */
    public boolean getHotel(){
        return numberOfHouses == 5;
    }

    /**
     * Method returns the total value of a property, which includes houses and hotels.
     * @return Integer value of the total property value.
     */
    @Override
    public int getWorth() {
        int worth = super.getWorth() + (numberOfHouses * housePrice);
        boolean hasHotel = false;
        if (hasHotel) {
            worth += housePrice * 5;
        }
        return worth;
    }

    /**
     * Method to add a house, if 4 houses already exists a hotel will be added and houses removed.
     */
    public void addHouse() {
        if (numberOfHouses <= 4) { //buying a house
            numberOfHouses++;
            housesInPlay++;
        } else if (numberOfHouses == 5) { //buying a hotel
            numberOfHouses++;
            housesInPlay -= 4;
            hotelsInPlay++;
        } else {
            throw new RuntimeException("Maximum amount of houses already reached.");
        }
    }


    /**
     * Removes multiple houses or hotels
     */
    public void removeHouse(int numberRemoved) {
        if (numberOfHouses >= 0 && numberOfHouses <= 4) {
            numberOfHouses -= numberRemoved;
            housesInPlay -= numberRemoved;
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
        return Values.rentPrice(p.getPos(), numberOfHouses);
    }

    /**
     * @return the total amount of houses on the board
     */
    public static int getHousesInPlay() {
        return housesInPlay;
    }

    /**
     * @return the total amount of hotels on the board
     */
    public static int getHotelsInPlay() {
        return hotelsInPlay;
    }
}
