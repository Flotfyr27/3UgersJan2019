package Domain.GameElements.Fields.Ownable;

import java.awt.*;
import Domain.GameElements.Entities.Player;

public class CompanyField extends OwnableField {
    public CompanyField(String name, String subtext, Color bgColour, int price){
        super(name, subtext, bgColour, price);
    }

    @Override
    public int getRent() {

    }

    public void landOnAction(Player current) {

    }
}
