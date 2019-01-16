package Domain.GameElements.Fields.Ownable;

import Domain.GameElements.Entities.Player;
import Domain.Controller.AuctionController;
import Domain.GameElements.Board;
import Domain.GameElements.Fields.Field;
import TechnicalServices.GameLogic.GameLogic;

import java.awt.*;

public abstract class OwnableField extends Field {
    private Player owner;
    protected boolean isPawned;
    protected int price, rent;


    /**
     * Constructor for all ownable fields
     *
     * @param name     The name of the field
     * @param subtext  The fields subtext
     * @param bgColour The background colour of the field
     * @param price    The price of the field
     */
    public OwnableField(String name, String subtext, Color bgColour, int price) {
        super(name, subtext, bgColour);
        this.price = price;
        this.isPawned = false;
    }

    /**
     * Returns the value of a field
     *
     * @return The total value of a field
     */
    public int getWorth() {
        int worth = price;
        return worth;
    }

    /**
     * Method to get the price of a field
     *
     * @return Integer value of the price of a field
     */
    public int getPrice() {
        return price;
    }

    /**
     * Method set the owner of a field.
     *
     * @param player Player to own the field
     */
    public void setOwner(Player player) {
        owner = player;
    }

    /**
     * Method to get the owner
     *
     * @return Player who is the owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Method to allow us to buy the fields
     * @param p
     */
    public void buyField(Player p) {
        if (p.getAccount().getScore() >= getPrice()) {
            try {
                setOwner(p);
                p.getAccount().changeScore(-getPrice());
                p.getOwnedFields().add(this);
            } catch (RuntimeException e) {
                GameLogic.cantPay(p, getPrice());
            }
        }
    }
    /**
     * Method to determine what happens when a player lands on a field.
     * @param current The current player
     */
    @Override
    public void landOnAction(Player current) {
        guiHandler.giveMsg("Du er landet på " + getName());
        if (getOwner() == null) {
            String choice = guiHandler.makeButtons("Vil du købe denne grund? Den koster " + price, "Ja", "Nej");
            if (choice.equalsIgnoreCase("Ja")) {
                buyField(current);
            } else {
                guiHandler.giveMsg("Grunden sættes op for auktion");
                AuctionController.getInstance().runCase(current);
            }
        } else if (getOwner() == current) {
            guiHandler.giveMsg("Du ejer dette felt");
            return;
        } else if (isPawned) {
            guiHandler.giveMsg("Denne grund er blevet pantet");
            return;

        } else {
            guiHandler.giveMsg("Du skal betale " + getRent(current) + "kr leje til  " + getOwner().getName());
            try {
                if (owner.getJailTime() < 0) {
                    guiHandler.giveMsg("Du skal betale " + getRent(current) + " i leje til  " + getOwner().getName());
                    boolean ownsAll = false;
                    for (OwnableField field : this.getFieldsOfColor()) {
                        if (field.getOwner() != null && field.getOwner().equals(owner))
                            ownsAll = true;
                        else {
                            ownsAll = false;
                            break;
                        }
                    }

                    if (ownsAll) {
                        if ((!this.getClass().equals(PropertyField.class)) || ((PropertyField) this).getHouses() == 0) {
                            current.getAccount().changeScore(-getRent(current) * 2);
                            getOwner().getAccount().changeScore(getRent(current) * 2); //TODO test that this works
                        } else {
                            current.getAccount().changeScore(-getRent(current));
                            getOwner().getAccount().changeScore(getRent(current));
                        }
                    } else {
                        current.getAccount().changeScore(-getRent(current));
                        getOwner().getAccount().changeScore(getRent(current));
                    }

                } else {
                    guiHandler.giveMsg(getOwner().getName() + " er i fængsel og kan derfor ikke kræve leje.");
                }
            } catch (RuntimeException e) {
                GameLogic.cantPay(current, getRent(current));
            }
        }
    }

    /**
     * Goes through all fields on the board and returns all the fields of the same
     * type and color as the one the method was called on.
     *
     * @return All fields of same color and class as the object
     */
    public OwnableField[] getFieldsOfColor() {
        Field[] fields = Board.getInstance().getFields();
        int colorFieldNum = 0;
        OwnableField[] fieldsOfColor;

            //counts the number fields of the same color and class as the object
            for (Field field : fields) {
                if (field.getClass().equals(this.getClass())) {
                    if (((OwnableField) field).getBgColor() == this.getBgColor()) {
                        colorFieldNum++;
                    }
                }
            }

            //Fills the array with the colored fields
            fieldsOfColor = new OwnableField[colorFieldNum];
            int colorIndex = 0;
            for (Field field : fields) {
                if (field.getClass().equals(this.getClass())) {
                    if (((OwnableField) field).getBgColor() == this.getBgColor()) {
                        fieldsOfColor[colorIndex++] = (OwnableField) field;
                    }
                }
            }
            return fieldsOfColor;
        }

        /**
         * returns the background color
         * @return
         */
        @Override
        public Color getBgColor () {
            return super.getBgColor();
        }

        public boolean getIsPawned () {
            return isPawned;
        }

        public void setIsPawned ( boolean changeTo){
            isPawned = changeTo;
        }

        public abstract int getRent (Player player);

        public int getHouses () {
            return 0;
        }
        public boolean getHotel () {
            return false;
        }
        public void removeHouse ( int value){
        }

        @Override
        public String toString () {
            return getName();
        }

}


