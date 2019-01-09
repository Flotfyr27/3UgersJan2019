package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.GameLogic;

public final class MoveToChanceCard extends MoveChanceCard {

    /**
     * Constructor. The destination is the fields place in the Field array Board.
     * The description is the text presented to the player
     *
     * @param destination
     * @param description
     */
    public MoveToChanceCard (int destination, String description){
        super(destination, description);
    }


    @Override
    public void action(Player p){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        GameLogic.movingPastStart(p, super.value);

        p.setPos(super.value);
    }
}
