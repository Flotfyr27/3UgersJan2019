package Domain.GameElements.Fields.Ownable;

import java.awt.*;

import Domain.Controller.MoveController;
import Domain.GameElements.Entities.DiceTray;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.Values;

public class CompanyField extends OwnableField {
    DiceTray diceTray;
    public CompanyField(String name, String subtext, Color bgColour, int price, DiceTray diceTray){
        super(name, subtext, bgColour, price);
        this.diceTray = diceTray;
    }

    @Override
    public int getRent(Player p) {
        int rent = Values.rentPrice(p.getPos(), 0);
        return rent * diceTray.getSum();
    }
}
