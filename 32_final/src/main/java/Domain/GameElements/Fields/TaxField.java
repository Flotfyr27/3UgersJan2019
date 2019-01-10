package Domain.GameElements.Fields;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
//import GUIHandler

import java.awt.*;

public class TaxField extends Field {
    private int tax, taxType;
    private Player player;

    /**
     * Constructor for taxField
     * @param name
     * @param subtext
     * @param bgColour
     * @param player
     * @param taxType
     */
    public TaxField(String name, String subtext, Color bgColour, Player player, int taxType) {
        super(name, subtext, bgColour);
        this.tax = tax;
        this.player = player;
        this.taxType = taxType;
        //GUI.getinstance
    }
    public void taxation(){

        if(taxType == 1){
            player.getAccount().changeScore(-2000);
        }else{

            if

        }
    }

}
/*
public class TaxCard extends ChanceCard{
    private int housePrice;
    private int hotelPrice;

    public TaxCard(int housePrice, int hotelPrice, String description){
        super(description);
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
    }

    public void action(Player p){
        int sum = 0;

        for (OwnableField f : p.getOwnedFields()){
            if (f.getClass().equals(PropertyField.class)) {
                PropertyField pf = (PropertyField) f;
                sum += housePrice * pf.getHouses();
                sum += hotelPrice * pf.getHotels();
            }
        }
    }
*/