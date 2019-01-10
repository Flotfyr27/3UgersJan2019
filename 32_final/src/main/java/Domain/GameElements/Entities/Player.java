package Domain.GameElements.Entities;

import Domain.GameElements.Fields.Ownable.OwnableField;

import java.util.ArrayList;

public class Player {
    private int pPos;
    private main.java.Domain.GameElements.Entities.Account account;
    private String name;
    private boolean isActive;
    private boolean lost;
    private ArrayList<OwnableField> ownedFields; /*we used an ArrayList instead of an array because
    this list will change size often over the course of a game*/

    /**
     * constructor.
     * @param name
     */
    public Player(String name) {
        this.name = name;
        pPos = 0;
        account = new main.java.Domain.GameElements.Entities.Account();
        isActive = true;
        lost = false;
        ownedFields = new ArrayList<OwnableField>();
    }

    /**
     * sets the isActive boolean used for control of when the player should be able to move and act no more.
     * @param isActive
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setPos(int pPos) {
        this.pPos = pPos;
    }

    public int getPos() {
        return pPos;
    }

    public main.java.Domain.GameElements.Entities.Account getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    /**
     * returns a boolean value of whether the player has lost yet or not.
     * @return
     */
    public boolean hasLost() {
        return lost;
    }

    /**
     * returns the ArrayList of the fields owned by the player.
     * @return
     */
    public ArrayList<OwnableField> getOwnedFields() {
        return ownedFields;
    }
}