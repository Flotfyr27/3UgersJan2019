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
        } else {
            sell(player);
        }
        guiHandler.updateGui(player, board.getPlayers(), board.getFields());
    }

    private void sell(Player player) {
        String[] possibleFields;
        String choice;
        PropertyField chosenField;

        //counts the number of fields the player owns which have houses on them
        int count = 0;
        for (OwnableField field : player.getOwnedFields()){
            if (field.getClass().equals(PropertyField.class))
                if (((PropertyField)field).getHouses() > 0)
                    count++;
        }

        //ads all the owned fields' names to the possibleFields array
        possibleFields = new String[count];
        for (int i = 0; i < player.getOwnedFields().size(); i++){
            OwnableField field = player.getOwnedFields().get(i);
            if (field.getClass().equals(PropertyField.class))
                if (((PropertyField)field).getHouses() > 0)
                    possibleFields[i] = field.getName();
        }

        //checks if any fields were found
        if (possibleFields.length < 1){
            guiHandler.giveMsg("Du har ingen grunde med huse på.");

        } else {
            //presents the player with their choices. String manipulation takes place for the sake of user experience.
            choice = guiHandler.makeButtons("Vælg en grund du vil sælge et hus fra. Antallet af huse på feltet " +
                    "står i parantes.", possibleFields);

            //Finds the chosen field and assigns it to chosenField
            chosenField = null;
            for (OwnableField field : player.getOwnedFields()){
                if (field.getName().equalsIgnoreCase(choice)) {
                    chosenField = (PropertyField) field;
                    break;
                }
            }

            if (chosenField != null) {
                player.getAccount().changeScore(Values.housePrice(chosenField.getBgColor()) / 2);
                chosenField.removeHouse(1);
            } else {
                throw new NullPointerException("the chosen field was not found");
            }
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
                triedColors = colorArrayAddition(triedColors, new Color[]{ownedField.getBgColor()});


                boolean sameOwner = true;
                for (OwnableField colorField : ownedField.getFieldsOfColor()) {
                    if (colorField.getOwner() == player)
                        sameOwner = true;
                    else {
                        sameOwner = false;
                        break;
                    }
                }

                if (sameOwner) {
                    //if you are here the player owns all fields of that color (for the specified type)

                    //fill the array with all the names of the fields of one color
                    String[] colorFields = new String[ownedField.getFieldsOfColor().length];

                    for (int i = 0; i < ownedField.getFieldsOfColor().length; i++) {
                        colorFields[i] = ownedField.getFieldsOfColor()[i].getName();
                    }

                    //add the array of newly found field names to the full list of fields able to get houses
                    ownableFields = stringArrayAddition(ownableFields, colorFields);
                }
            }
        }

        //TODO Make check that ensures one can only build on fields that has the least amount of houses
        //TODO make check for number of houses to add hotels and cap the amount of times you can buy
        //TODO allow the player to buy multiple buildings at a time
        //TODO use getBgColor in Values.getHousePrice instead of number of houses

        if (ownableFields.length <= 0) {
            guiHandler.giveMsg("Du har ingen grunde du kan købe huse på.");
        } else {
            chosenField = getChosenField(player, ownableFields);
            if (chosenField == null){
                guiHandler.giveMsg("Noget gik galt :(");
            } else {
                //Checks if the player wants to by the house
                if (guiHandler.makeButtons("Vil du bygge et hus/hotel på " + chosenField.getName() + " for kr. " +
                                Values.housePrice(chosenField.getBgColor()) + "?",
                        "Ja", "Nej").equalsIgnoreCase("Ja")) {
                    if (player.getAccount().canBuy(Values.housePrice(chosenField.getBgColor()))) {
                        player.getAccount().changeScore(-Values.housePrice(chosenField.getBgColor()));
                        chosenField.addHouse();
                    } else {
                        guiHandler.giveMsg("Du har ikke råd til at bygge dette hus/hotel");
                    }
                }
            }
        }

    }


    private PropertyField getChosenField(Player owner, String[] fieldNames) {
        //Select a field to trade based on user input
        if (fieldNames.length > 0) {
            String fieldString = guiHandler.makeButtons("Vælg felt du vil købe/sælge huse på", fieldNames);
            for (int n = 0; n < owner.getOwnedFields().size(); n++) {
                if (fieldString.equals(fieldNames[n])) {
                    try {
                        return (PropertyField) owner.getOwnedFields().get(n);
                    } catch (ClassCastException e) {
                        guiHandler.giveMsg("Noget gik galt :(");
                        System.out.println("Field received was not a PropertyField");
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return null;
        }
        throw new RuntimeException("getChosenField() returned no value");
    }

    private String[] stringArrayAddition(String[] arr1, String[] arr2){
        String[] temp = new String[arr1.length + arr2.length];

        for (int i = 0; i < arr1.length; i++) {
            temp[i] = arr1[i];
        }

        for (int i = 0; i < arr2.length; i++) {
            temp[arr1.length + i] = arr2[i];
        }
        return temp;
    }


    private Color[] colorArrayAddition(Color[] arr1, Color[] arr2){
        Color[] temp = new Color[arr1.length + arr2.length];

        for (int i = 0; i < arr1.length; i++) {
            temp[i] = arr1[i];
        }

        for (int i = 0; i < arr2.length; i++) {
            temp[arr1.length + i] = arr2[i];
        }
        return temp;
    }

    /**
     * A method for adding the number of houses on a field to the field-selection buttons
     * @param fieldList the list of fields that is being changed
     * @return
     */

}