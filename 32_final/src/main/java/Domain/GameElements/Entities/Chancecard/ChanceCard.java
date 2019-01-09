package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Entities.Player;

public abstract class ChanceCard {
    protected String description;

    /**
     * the method that activates any ChanceCards' effect.
     * @param player
     */
    public abstract void action (Player player);
}
