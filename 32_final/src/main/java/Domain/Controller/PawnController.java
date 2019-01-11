package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;

public class PawnController {

    private int pawnValue;
    private boolean fieldIsEmpty;
    Player player = new Player("Pawner");




    private void pawnProperty() {
        player.getOwnedFields();
        if (fieldIsEmpty = true){
            int ownableWorth = 0;
            for (OwnableField field : player.getOwnedFields()) {
                ownableWorth = field.getWorth() - field.getPrice();

            }


        }
        else {

        }
    }


    }

