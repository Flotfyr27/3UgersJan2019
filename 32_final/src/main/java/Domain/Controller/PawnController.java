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


    public PawnController(Player[] players, Field[] fields) {

    }

    private boolean FieldIsEmpty() {
        if (propertyField.getHouses() == 0 || propertyField.getHotel()) {
            fieldIsEmpty = true;
        } else {
            fieldIsEmpty = false;
        }
        return false;
    }

    private boolean ifFieldIsPropertyField(){
        if (ownableField.getClass()==PropertyField.class){
            fieldIsPropertyField = true;
        }
        else{
            fieldIsPropertyField = false;
        }
        return false;
    }

    private void pawnProperty() {
        int buildingsWorth = 0;
        buildingsWorth = field.getWorth() - field.getPrice();
        if (fieldIsPropertyField && !fieldIsEmpty){

            int numberOfHouses = propertyField.getHouses();
            propertyField.removeHouse(numberOfHouses);
            account.changeScore(buildingsWorth);

        } else {

            pawnValue = ownableField.getPrice() / 2;

            account.changeScore(-pawnValue);
        }

    private void unPawn(){
            account.changeScore(pawnValue);
            
        }
    }
}




