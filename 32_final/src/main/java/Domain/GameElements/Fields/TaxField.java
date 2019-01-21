package Domain.GameElements.Fields;

import Domain.Controller.PawnController;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
import TechnicalServices.GameLogic.GameLogic;
import UI.GUI.GuiHandler;
//import GUIHandler

import java.awt.*;

public class TaxField extends Field {
    private int tax, taxType;
    private GuiHandler guiHandler;

    /**
     * Constructor for taxField
     *
     * @param name the name of the field
     * @param subtext A short description of the fields effect
     * @param bgColour The color of the field
     * @param taxType The types of taxes possible
     */
    public TaxField(String name, String subtext, Color bgColour, int taxType) {
        super(name, subtext, bgColour);
        this.taxType = taxType;

        try {
            guiHandler = GuiHandler.getInstance();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * LandOnAction that handles the two taxation fields
     *
     * @param player the player landing on the field
     */

    @Override
    public void landOnAction(Player player) {

        int sum = 0;
        /*
         * for field 'Ekstraordinær statsskat'
         */
        if (taxType == 1) {
            guiHandler.giveMsg("Betaling af skatter: 2000 kr");
            try {
                player.getAccount().changeScore(-2000);
            } catch(RuntimeException e){
                GameLogic.cantPay(player,-2000);
            }
        } else {
            /*
             * for field 'Indkomstskat'
             */
            String buttons = guiHandler.makeButtons("Betal indskomstskat: 10% af alt du ejer (bygninger, huse og " +
                    "i beløbet på din konto) eller kr.4.000", "10%", "kr.4.000");
            if (buttons.equals("10%")) {
                /*
                 * For loop which calculates the tax of 10% if that option is selected. Using the ownedFields arrayList in player.
                 */
                for (OwnableField field : player.getOwnedFields()) {
                    sum += field.getWorth();
                }
                sum += player.getAccount().getScore();
                sum = (int) Math.round(sum * 0.1);
                try{
                player.getAccount().changeScore(-sum);
                guiHandler.giveMsg("Du betalte kr. " + sum + " i skat.");
                }catch(RuntimeException e){
                    GameLogic.cantPay(player,-sum);
                }
                /*
                 * Selected option of paying kr.4.000
                 */
            } else if (buttons.equals("kr.4.000")) {
                try{
                    player.getAccount().changeScore(-4000);
                }catch(RuntimeException e){
                    GameLogic.cantPay(player,-4000);
                }
            } else {
                throw new IllegalArgumentException("Value doesn't correspond to either button choice");
            }

        }

    }
}
