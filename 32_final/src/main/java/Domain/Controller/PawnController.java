package Domain.Controller;


import Domain.GameElements.Entities.Chancecard.MoveToNearestChanceCard;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Entities.Account;

public class PawnController {

    private int pawnValue;
    private boolean fieldIsEmpty;
    private boolean fieldIsPropertyField;
    private MoveToNearestChanceCard moveToNearestChanceCard;
    private Class<OwnableField> type;

    Player[] players;
    private PropertyField propertyField;

    private Account account = new Account();
    private OwnableField ownableField;
    private Field[] field;


    /**
     * constructor
     * @param players
     * @param fields
     */
    public PawnController(Player[] players, Field[] fields) {

    }

    /**
     * Boolean checking if the field has any hotels or houses
     * @return
     */
    private boolean FieldIsEmpty() {
        if (propertyField.getHouses() == 0 || propertyField.getHotel()) {
            fieldIsEmpty = true;
        } else {
            fieldIsEmpty = false;
        }
        return false;
    }

    /**
     * Boolean checking if it is a Property field
     * @return
     */
    private boolean ifFieldIsPropertyField(){
        if (ownableField.getClass()==PropertyField.class){
            fieldIsPropertyField = true;
        }
        else{
            fieldIsPropertyField = false;
        }
        return false;
    }

    /**
     * Method that pawn our properties.
     */
    private void pawnProperty() {
        int buildingsWorth = 0;
        buildingsWorth = propertyField.getWorth() - propertyField.getPrice();
        if (fieldIsPropertyField && !fieldIsEmpty) { //removes the houses before pawning it

            int numberOfHouses = propertyField.getHouses();
            propertyField.removeHouse(numberOfHouses);
            account.changeScore(buildingsWorth);

        } else {

            pawnValue = ownableField.getPrice() / 2;

            account.changeScore(pawnValue);
        }

    }

    /**
     * Unpawning the properties.
     */
    private void buyPawnBack(){
        account.changeScore((int) (-pawnValue*1.1-((int)(pawnValue*1.1)%50)));



    }
    private void unPawn(){
   if(ownableField.setIsPawned(false)){
       buyPawnBack();

    }

   }

}






