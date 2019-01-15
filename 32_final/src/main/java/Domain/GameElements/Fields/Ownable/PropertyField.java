package Domain.GameElements.Fields.Ownable;

import java.awt.*;

import Domain.Controller.AuctionController;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.Values;
import UI.GUI.GuiHandler;

public class PropertyField extends OwnableField {

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
    public int getHouses() {
        return numberOfHouses;
    }

    /**
     * Method returns boolean value depending on the presence of a hotel
     *
     * @return True/false if field has a hotel
     */
    public boolean getHotel() {
        return hasHotel;
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
        if (hasHotel) {

        } else if (numberOfHouses < 4) {
            numberOfHouses++;
        } else if (!hasHotel && numberOfHouses == 4) {
            hasHotel = true;
            numberOfHouses = 0;
        } else {
            return;
        }
    }

    /**
     * Removes a house based on the numeric value given
     * @param value The amount of houses to remove
     */
    public void removeHouse(int value) {
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

    /**
     * Method to determine what happens when a player lands on a field.
     * @param current The current player
     */
    @Override
    public void landOnAction(Player current) {
        if (getOwner() == null) {
            String choice = guiHandler.makeButtons("Vil du købe denne grund?", "Ja", "Nej");
            if (choice.equalsIgnoreCase("Ja")) {
                if (current.getAccount().canBuy(-getPrice())) {
                    current.getAccount().changeScore(-getPrice());
                    setOwner(current);
                    current.getOwnedFields().add(this);
                } else {
                    guiHandler.giveMsg("Du har desværre ikke råd til denne ejendom");
                    //TODO call an auctionController here
                }
            } else {
                AuctionController.getInstance().runCase(current);
            }
        } else {
            current.getAccount().canBuy(-rent);
        }
    }

    /**
     * This method gives the price of buying a house
     * @param p The player who lands on the field
     * @return Returns an integer with the price of buying a house on current propertyField
     */
    public int getHousePrice(Player p){
        int priceOfHouse = housePrice;
        return priceOfHouse;
    }
}
