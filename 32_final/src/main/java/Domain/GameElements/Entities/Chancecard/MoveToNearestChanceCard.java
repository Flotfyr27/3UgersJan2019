package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Field;

public class MoveToNearestChanceCard extends MoveToChanceCard {

    private Class<Field> type;
    private Field[] fields;

    public MoveToNearestChanceCard(Class<Field> type, Field[] fields, String description){
        super(0, description);
        this.type = type;
        this.fields = fields;
    }

    public void action(Player p) {
        for (int i = p.getPos(); i < fields.length; i++) {
            if (fields[i].getClass().equals(type)) {
                super.value = i;
                super.action(p);
                return;
            }
        }
        for (int i = 0; i < p.getPos(); i++) {
            if (fields[i].getClass().equals(type)) {
                super.value = i;
                super.action(p);
                return;
            }
        }
        throw new RuntimeException("No Field of the specified type found");
    }

}
