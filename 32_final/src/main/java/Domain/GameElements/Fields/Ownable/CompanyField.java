package Domain.GameElements.Fields.Ownable;

import java.awt.*;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.Values;

public class CompanyField extends OwnableField {
    public CompanyField(String name, String subtext, Color bgColour, int price){
        super(name, subtext, bgColour, price);
    }

    @Override
    public int getRent(Player p) {
        int count = 0;
        for(int n = 0; n < getOwner().getOwnedFields().size(); n++){
            if(getOwner().getOwnedFields().get(n).getClass().equals(CompanyField.class)){
                count++;
            }
        }
        int rent = Values.rentPrice(p.getPos(), count);
        return rent;
    }

    public void landOnAction(Player current) {

    }
}
