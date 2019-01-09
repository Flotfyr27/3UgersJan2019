package Domain.GameElements.Entities.Chancecard;

public final class MoveToChanceCard extends MoveChanceCard {
    private int destination;

    /**
     * Constructor. The destination is the fields place in the Field array Board.
     * The description is the text presented to the player
     *
     * @param destination
     * @param description
     */
    public MoveToChanceCard (int destination, String description){
        this.destination = destination;
        super.description = description;
    }


    private void action(Player p){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        GameLogic.passStart();

        p.setPos(destination);
        p.getAccount().changeScore(amount);
    }
}
