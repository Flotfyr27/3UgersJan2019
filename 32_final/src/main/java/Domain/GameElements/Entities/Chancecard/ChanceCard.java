package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Entities.Player;

public abstract class ChanceCard {
    protected String description;

    /**
     * Constructor.
     *
     * @param description The text displayed on the ChanceCard
     */
    protected ChanceCard(String description){
        this.description = description;
    }

    /**
     * The method that activates any ChanceCards' effect.
     *
     * @param player The player who activates the ChanceCard
     */
    public abstract void action (Player player);

    /**
     * An override of the toString method native to the 'Object' class
     *
     * @return The description of the ChanceCard
     */
    @Override
    public String toString(){
        return description;
    }
}
