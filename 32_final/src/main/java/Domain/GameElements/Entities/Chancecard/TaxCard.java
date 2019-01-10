package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.*;
import UI.GUI.*;


public class TaxCard extends ChanceCard{
    private int housePrice;
    private int hotelPrice;
    private GuiHandler gui;

    public TaxCard(int housePrice, int hotelPrice, String description){
        super(description);
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
        gui = GuiHandler.getInstance();
    }

    public void action(Player p){

    }


}
