package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;

public class GetOutOfJailCard extends ChanceCard{

    /**
     * Constructor.
     *
     * @param description The text displayed on the ChanceCard
     */
    public GetOutOfJailCard(String description) {
        super(description);
    }


    /**
     * The method that activates any ChanceCards' effect.
     *
     * @param player The player who activates the ChanceCard
     */
    @Override
    public void action(Player player) {
        player.addJailCards();

    }
}
