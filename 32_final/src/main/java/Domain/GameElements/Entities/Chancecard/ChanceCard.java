package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Entities.Player;

public abstract class ChanceCard {
    protected String description;

    public abstract void action (Player player);
}
