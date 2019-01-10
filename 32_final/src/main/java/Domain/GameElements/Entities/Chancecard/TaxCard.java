package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.*;


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
                if(pf.getHotel())
                    sum++;
            }
        }
    }


}
