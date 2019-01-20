package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.*;
import TechnicalServices.GameLogic.GameLogic;


public class TaxCard extends ChanceCard{
    private int housePrice;

    /**
     * Constructor
     *
     * @param housePrice The price payed for one house
     * @param description The text displayed on the ChanceCard
     */
    public TaxCard(int housePrice, String description){
        super(description);
        this.housePrice = housePrice;
    }

    /**
     * Takes money from the player depending on the amount of houses they owns
     *
     * @param p The player drawing the card
     */
    public void action(Player p){
        int sum = 0;

        for (OwnableField f : p.getOwnedFields()){
            if (f.getClass().equals(PropertyField.class)) {
                PropertyField pf = (PropertyField) f;
                sum += housePrice * pf.getHouses();
                if(pf.getHotel())
                    sum++;
            }
        }
        try {
            p.getAccount().changeScore(-sum);
        } catch(RuntimeException e){
            GameLogic.cantPay(p,-sum);
        }
    }
}
