package Domain.Controller;


import Domain.GameElements.Entities.Chancecard.MoveToNearestChanceCard;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Entities.Account;
import UI.GUI.GuiHandler;

public class PawnController {

   // private int pawnValue;
    private boolean fieldIsEmpty;
    private boolean fieldIsPropertyField;
    private MoveToNearestChanceCard moveToNearestChanceCard;
    private Class<OwnableField> type;

    Player[] players;
    private PropertyField propertyField;

    private Account account = new Account();
    private Field[] field;


    /**
     * constructor
     *
     */
    public PawnController() {

    }

    public void runCase(Player player){
        String pawnedField = null;
        OwnableField field = null;
        String fieldNames[] = new String[28];
        for(int n = 0; n < player.getOwnedFields().size(); n++){
            fieldNames[n] = player.getOwnedFields().get(n).getName();
        }
        //Select a field to pawn based on user input
        pawnedField = GuiHandler.getInstance().makeButtons("Vælg et felt du vil pante", fieldNames);
        for(int n = 0; n < player.getOwnedFields().size(); n++){
            if(pawnedField.equals(fieldNames[n])){
                field = player.getOwnedFields().get(n);
            }
            pawnProperty(field);
        }
    }
    /**
     * Boolean checking if the field has any hotels or houses
     *
     * @return
     */
    private boolean hasBuildings() {
        if (propertyField.getHouses() == 0 && propertyField.getHotel()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method checks if the field is a property field
     *
     * @return
     */
    private boolean isPropertyField(OwnableField ownableField) {
        if (ownableField.getClass() == PropertyField.class) {
            fieldIsPropertyField = true;
        } else {
            fieldIsPropertyField = false;
        }
        return fieldIsPropertyField;
    }

    private int pawnValue(OwnableField ownableField) {
        return ownableField.getPrice() / 2;

    }

    /**
     * Method that pawn our properties.
     */
    private void pawnProperty(OwnableField ownableField) {
        int buildingsWorth = 0;
        buildingsWorth = ownableField.getWorth() - ownableField.getPrice();

        if (!hasBuildings() && isPropertyField(ownableField)) {
            int numberOfHouses = ownableField.getHouses();
            ownableField.removeHouse(numberOfHouses);
            account.changeScore(buildingsWorth);
        } else {
            account.changeScore(pawnValue(ownableField));
        }
            ownableField.setIsPawned(true);
    }

    /**
     * Multiplication of
     * Cast the double as an int.
     */
    private void buyPawnBack(OwnableField ownableField) {

        account.changeScore((int) (-pawnValue(ownableField) * 1.1 - ((int) (pawnValue(ownableField) * 1.1) % 50)));

    }

    /**
     * Getting boolean from ownableField and allows you to unpawn it.
     */
    private void unPawn(OwnableField ownableField) {
        if (ownableField.getIsPawned()) {
            buyPawnBack(ownableField);
            ownableField.setIsPawned(false);
        }

    }

}

