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
        } else {
            sell(player);
        }

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
            addHouseCount(player, possibleFields);
            choice = guiHandler.makeButtons("Vælg en grund du vil sælge et hus fra. Antallet af huse på feltet " +
                    "står i parantes.", possibleFields);
            removeHouseCount(choice);

            //Finds the chosen field and assigns it to chosenField
            chosenField = null;
            for (OwnableField field : player.getOwnedFields()){
                if (field.getName().equalsIgnoreCase(choice)) {
                    chosenField = (PropertyField) field;
                    break;
                }
            }

            if (chosenField != null) {
                player.getAccount().changeScore(Values.housePrice(chosenField.getHouses()));
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
                triedColors = colorArrayAddition(triedColors, new Color[]{ownedField.getBgColor()}); //TODO look at me!!!


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


    private PropertyField getChosenField(Player owner, String[] fieldNames) {
        //Select a field to trade based on user input
        if (fieldNames.length > 0) {
            String fieldString = guiHandler.makeButtons("Vælg felt du vil købe/sælge huse på", fieldNames);
            for (int n = 0; n < owner.getOwnedFields().size(); n++) {
                if (fieldString.equals(fieldNames[n])) {
                    return (PropertyField) owner.getOwnedFields().get(n);
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
    private void addHouseCount(Player player, String[] fieldList){
        for (String fieldName : fieldList){
            for (OwnableField field : player.getOwnedFields()){
                if (field.getClass().equals(PropertyField.class)) {
                    if (field.getName().equalsIgnoreCase(fieldName)) {
                        fieldName = fieldName.concat("-(" + ((PropertyField)field).getHouses() + ")");
                    }
                }
            }
        }
    }

    /**
     * Removes the house count from the string to make it usefull for serching for a property again.
     * use it on the returned value from a button press.
     * @param returnedString the string returned by the makeButtons() function
     */
    private void removeHouseCount(String returnedString){
        returnedString = returnedString.split("-")[0];
    }
}