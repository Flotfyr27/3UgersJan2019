package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Field;

public class MoveToNearestChanceCard extends ChanceCard {
    private java.Class fieldType;

    public MoveToNearestChanceCard(Field.Class fieldType, String description){
        super(description);
        this.fieldType = fieldType;
    }

    public void action(Player player) {

    }

}
