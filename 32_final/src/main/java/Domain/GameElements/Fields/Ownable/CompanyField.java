package Domain.GameElements.Fields.Ownable;

import java.awt.*;

import Domain.Controller.MoveController;
import Domain.GameElements.Entities.DiceTray;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.Values;

public class CompanyField extends OwnableField {
    private DiceTray diceTray;

    /**
     * Constructor
     * @param name The name of the field
     * @param subtext A short description of it's effect or other useful info
     * @param bgColour The colour of the field
     * @param price The price for buying this field.
     * @param diceTray The diceTray used by the players
     */
    public CompanyField(String name, String subtext, Color bgColour, int price, DiceTray diceTray){
        super(name, subtext, bgColour, price);
        this.diceTray = diceTray;
    }

    /**
     * calculates rent based on the dice roll of the player landing on the field
     *
     * @param p The player landing on the field
     * @return The rent for landing on the field
     */
    @Override
    public int getRent(Player p) {
        int rent = Values.rentPrice(p.getPos(), 0);
        return rent * diceTray.getSum();
    }
}
