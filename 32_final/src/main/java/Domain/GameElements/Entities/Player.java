package Domain.GameElements.Entities;

import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Entities.Account;
import java.util.ArrayList;

public class Player {
    private int pPos;
    private Account account;
    private String name;
    private boolean isActive;
    private boolean lost;
    private ArrayList<OwnableField> ownedFields; /*we used an ArrayList instead of an array because
    this list will change size often over the course of a game*/
    private int jailCards;
    private int jail;


    /**
     * constructor.
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        pPos = 0;
        account = new Account();
        isActive = true;
        lost = false;
        ownedFields = new ArrayList<OwnableField>();
        jailCards = 0;
        jail = -1;
    }

    /**
     * sets the isActive boolean used for control of when the player should be able to move and act no more.
     * @param isActive a boolean that decides if the player can act again.
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * gets the isActive boolean used for control of when the player should be able to move and act no more.
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * changes the players position on the board.
     * @param pPos The target position.
     */
    public void setPos(int pPos) {
        this.pPos = pPos;
    }

    /**
     * @return the position of the player
     */
    public int getPos() {
        return pPos;
    }

    /**
     * @return Returns the players account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @return returns the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * JailTime keeps track of how long the player have been to jail.
     * -1 means the player isn't in jail
     * 0 means they haven't yet had their first turn in jail.
     *
     * @param jail The number of rounds the player has been to jail in.
     */
    public void setJailTime(int jail) {
        this.jail = jail;
    }

    /**
     * @return returns the number of rounds the player has been to jail. -1 if they aren't in jail.
     */
    public int getJailTime() {
        return jail;
    }

    /**
     * @return returns true if the player has lost.
     */
    public boolean hasLost() {
        return lost;
    }

    /**
     * Changes the players state, normally from not having lost (false) to having lost (true)
     * @param state A boolean deciding if the player has lost.
     */
    public void setLost(boolean state){lost = state;}

    /**
     * @return returns the ArrayList of the fields owned by the player.
     */
    public ArrayList<OwnableField> getOwnedFields() {
        return ownedFields;
    }

    /**
     * Methods that ensures that player gets an extra jailCard if the GetOutOfJailFree card is drawn.
     */
    public void addJailCards(){
        jailCards++;
    }

    /**
     * Method removes a jailCard from the player
     */
    public void removeJailCards(){
        jailCards--;
    }

    /**
     * Method returns the number of jailCards in possession of the player
     * @return Integer value depending on the amount of jailCards
     */
    public int getJailCards(){
        return jailCards;
    }

}