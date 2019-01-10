package Domain.GameElements.Fields.Ownable;

import java.awt.*;

public class CompanyField extends OwnableField {
    public CompanyField(String name, String subtext, Color bgColour, Player owner, int price, int rent){
        super(name, subtext, bgColour, owner, price, rent);
    }

    @Override
    public void buy() {

    }

    @Override
    public void sell() {

    }

    @Override
    public void pawn() {

    }

    @Override
    public void payRent() {

    }
}
