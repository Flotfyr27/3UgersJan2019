package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Entities.Account;

public class PawnController {

    private int pawnValue;
    private boolean fieldIsEmpty;

    Player player = new Player();
    PropertyField propertyField = new PropertyField();

    Account account = new Account();
    OwnableField ownableField;




    private boolean FieldIsEmpty() {
        this.fieldIsEmpty = fieldIsEmpty;
        if (propertyField.getHouses() == 0 || propertyField.getHotel()) {
            fieldIsEmpty = true;
        } else {
            fieldIsEmpty = false;
        }
        return false;
    }

        private void pawnProperty(){

            if (fieldIsEmpty == true) {
                pawnValue = ownableField.getPrice() / 2;

                account.changeScore(-pawnValue);

            }
            else{
                int numberOfHouses = propertyField.getHouses();
                propertyField.removeHouse(numberOfHouses);
            }
        }
        /**if (fieldIsEmpty == true){
            int buildingsWorth = 0;
            for (OwnableField field : player.getOwnedFields()) {

                buildingsWorth = field.getWorth() - field.getPrice();



            }
            int numberOfHouses = propertyField.getHouses();
            propertyField.removeHouse(numberOfHouses);
        }
        else {
        */
        }
    }



