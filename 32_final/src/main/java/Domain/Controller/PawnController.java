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
    private boolean hasBuildings() {
        if (propertyField.getHouses() == 0 && propertyField.getHotel()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method checks if the field is a property field
     * @return
     */
    private boolean isPropertyField(){
        if (ownableField.getClass()==PropertyField.class){
            fieldIsPropertyField = true;
        }
        else{
            fieldIsPropertyField = false;
        }
        return fieldIsPropertyField;
    }

    private int pawnValue(){
        pawnValue = ownableField.getPrice()/2;
    return pawnValue;
    }

    /**
     * Method that pawn our properties.
     */
    private void pawnProperty() {
        int buildingsWorth = 0;
        buildingsWorth = propertyField.getWorth() - propertyField.getPrice();

        if (!hasBuildings() && isPropertyField()) {
            int numberOfHouses = propertyField.getHouses();
            propertyField.removeHouse(numberOfHouses);
            account.changeScore(buildingsWorth);

        } else {

            account.changeScore(pawnValue());
        }

    }

    /**
     * Multiplication of
     * Cast the double as an int.
     */
    private void buyPawnBack(){

        account.changeScore((int) (-pawnValue()*1.1-((int)(pawnValue()*1.1)%50)));

    }

    /**
     * Getting boolean from ownableField and allows you to unpawn it.
      */
    private void unPawn(){
   if(ownableField.setIsPawned(false)){
       buyPawnBack();

    }

   }

}



/*
køb grunde tilbage x
pantsætgrunde x
tjek om der er huse på grunden  x
 */


