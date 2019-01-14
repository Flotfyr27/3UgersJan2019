package Domain.GameElements.Fields;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
import UI.GUI.GuiHandler;
//import GUIHandler

import java.awt.*;

public class TaxField extends Field {
    private int tax, taxType;
    private GuiHandler guiHandler;

    /**
     * Constructor for taxField
     *
     * @param name
     * @param subtext
     * @param bgColour
     * @param taxType
     */
    public TaxField(String name, String subtext, Color bgColour, int taxType) {
        super(name, subtext, bgColour);
        this.tax = tax;
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
     * @param player
     */

    @Override
    public void landOnAction(Player player) {

        int sum = 0;
        /*
         * Ekstraordinær statsskat
         */
        if (taxType == 1) {
            player.getAccount().changeScore(-2000);
        } else {
            /*
             * Indkomstskat
             */
            String buttons = guiHandler.makeButtons("Betal indskomstskat: 10% eller kr.4.000", "10%", "kr.4.000");
            if (buttons.equals("10%")) {
                /*
                 * For loop which calculates the tax of 10% if that option is selected. Using the ownedFields arrayList in player.
                 */
                for (OwnableField field : player.getOwnedFields()) {
                    sum += field.getWorth();
                }
                sum = (int) Math.round(sum * 0.1);
                player.getAccount().changeScore(-sum);

                /*
                 * Selected option of paying kr.4.000
                 */
            } else if (buttons.equals("kr.4.000")) {
                player.getAccount().changeScore(-4000);
            } else {
                throw new IllegalArgumentException("Value doesn't correspond to either button choice");
            }

        }

    }
}
