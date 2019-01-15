package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
import TechnicalServices.GameLogic.Values;
import UI.GUI.GuiHandler;


import java.awt.*;

public class BuySellController {
    private GuiHandler guiHandler = GuiHandler.getInstance();
    private boolean notDone;
    private Board board;

    private static BuySellController instance;

    /**
     * Making the BuySellController a singleton
     *
     * @return
     */
    public static BuySellController getInstance() {
        if (instance == null) {
            instance = new BuySellController();
            return instance;
        } else {
            return instance;
        }
    }

    private BuySellController() {
        board = Board.getInstance();
    }


    public void runCase(Player player) {



        String buttons = guiHandler.makeButtons("Vil du købe eller sælge huse/hoteller?", "Køb", "Sælg");


        if (buttons.equalsIgnoreCase("Køb")) {
            buy(player);
        }

    }

    private void buy(Player player){
        Color[] triedColors = new Color[0];
        String[] ownableFields = new String[0];
        PropertyField chosenField;


        for (OwnableField ownedField : player.getOwnedFields()) {

            //checks if color has already been checked
            boolean isChecked = false;
            for (Color triedColor : triedColors) {
                if (ownedField.getBgColor().equals(triedColor)) {
                    isChecked = true;
                    break;
                }
            }

            if (!isChecked) {
                triedColors = (Color[]) arrayAddition(triedColors, new Color[]{ownedField.getBgColor()});


                boolean sameOwner;
                for (OwnableField colorField : ownedField.getFieldsOfColor()) {
                    if (colorField.getOwner() == player)
                        sameOwner = true;
                    else {
                        sameOwner = false;
                        break;
                    }

                    //if you are here the player owns all fields of that color (for the specified type)

                    //fill the array with all the names of the fields of one color
                    String[] colorFields = new String[ownedField.getFieldsOfColor().length];

                    for (int i = 0; i < colorField.getFieldsOfColor().length; i++) {
                        colorFields[i] = colorField.getFieldsOfColor()[i].getName();
                    }

                    //add the array of newly found field names to the full list of fields able to get houses
                    ownableFields = (String[]) arrayAddition(ownableFields, colorFields);
                }
            }
        }

        //TODO Make check that ensures one can only build on fields that has the least amount of houses
        //TODO make check for number of houses to add hotels and cap the amount of times you can buy

        if (ownableFields.length <= 0) {
            guiHandler.giveMsg("Du har ingen grunde du kan købe huse på.");
        } else {
            chosenField = (PropertyField) getChosenField(player, ownableFields);
            //Checks if the player wants to by the house
            if (guiHandler.makeButtons("Vil du bygge et hus/hotel på " + chosenField.getName() + " for kr. " +
                            Values.housePrice(chosenField.getHouses() + 1) + "?",
                    "Ja", "Nej").equalsIgnoreCase("Ja"))
            {
                if (player.getAccount().canBuy(Values.housePrice(chosenField.getHouses() + 1))){
                    player.getAccount().changeScore(-Values.housePrice(chosenField.getHouses() + 1));
                    chosenField.addHouse();
                } else {
                    guiHandler.giveMsg("Du har ikke råd til at bygge dette hus/hotel");
                }
            }
        }

    }


    private OwnableField getChosenField(Player owner, String[] fieldNames) {
        //Select a field to trade based on user input
        if (fieldNames.length > 0) {
            String fieldString = guiHandler.makeButtons("Vælg felt du vil bygge på", fieldNames);
            for (int n = 0; n < owner.getOwnedFields().size(); n++) {
                if (fieldString.equals(fieldNames[n])) {
                    return owner.getOwnedFields().get(n);
                }
            }
        } else {
            return null;
        }
        throw new RuntimeException("getChosenField() returned no value");
    }

    private Object arrayAddition(Object[] arr1, Object[] arr2){
        Object[] temp = new Object[arr1.length + arr2.length];

        for (int i = 0; i < arr1.length; i++) {
            temp[i] = arr1[i];
        }

        for (int i = 0; i < arr2.length; i++) {
            temp[arr1.length + i] = arr2[i];
        }
        return temp;
    }
}