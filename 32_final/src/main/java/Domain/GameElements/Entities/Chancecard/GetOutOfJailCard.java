package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;

public class GetOutOfJailCard extends ChanceCard{

    /**
     * Constructor.
     *
     * @param description
     */
    public GetOutOfJailCard(String description) {
        super(description);
    }

    public void action(Player player) {
        player.addJailCards();

    }
}
