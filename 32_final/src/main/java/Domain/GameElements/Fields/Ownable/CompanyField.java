package Domain.GameElements.Fields.Ownable;

import java.awt.*;
import Domain.GameElements.Entities.Player;

public class CompanyField extends OwnableField {
    public CompanyField(String name, String subtext, Color bgColour, int price){
        super(name, subtext, bgColour, price);
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

    public void landOnAction(Player current) {

    }
}
