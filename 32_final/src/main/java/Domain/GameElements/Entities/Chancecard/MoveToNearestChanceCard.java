package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Fields.Ownable.OwnableField;

public class MoveToNearestChanceCard extends MoveToChanceCard {

    private Class<OwnableField> type;
    private Field[] fields;
    private boolean payDouble;

    /**
     * Constructor
     *
     * @param type
     * @param fields
     * @param description
     */
    public MoveToNearestChanceCard(Class<OwnableField> type, Field[] fields, String description){
        super(0, description);
        this.type = type;
        this.fields = fields;
        payDouble = false;
    }

    /**
     * Constructor
     *
     * @param type
     * @param fields
     * @param payDouble
     * @param description
     */
    public MoveToNearestChanceCard(Class<OwnableField> type, Field[] fields, boolean payDouble, String description){
        super(0, description);
        this.type = type;
        this.fields = fields;
        this.payDouble = payDouble;
    }

    /**
     * moves the player to the closest field of a specified class.
     *
     * @param p
     *
     * TODO implement the payDouble feature
     */
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
