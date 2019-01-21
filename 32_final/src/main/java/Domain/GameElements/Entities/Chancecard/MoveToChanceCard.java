package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.GameLogic;
import UI.GUI.GuiHandler;

public class MoveToChanceCard extends MoveChanceCard {
    private boolean getsStartMoney;

    /**
     * Constructor. The destination is the fields place in the Field array Board.
     * The description is the text presented to the player.
     *
     * @param destination The location of the field being moved to(index in the field array in Board)
     * @param description The text being displayed on the ChanceCard
     */
    public MoveToChanceCard (int destination, String description){
        super(destination, description);
        getsStartMoney = true;
    }

    /**
     * Constructor.
     * The destination is the fields place in the Field array Board.
     * getsStartMoney informs whether the player gets money if they pass start.
     * The description is the text presented to the player.
     *
     * @param destination  The location of the field being moved to (index in the field array in Board)
     * @param description The text being displayed on the ChanceCard
     * @param getsStartMoney A boolean deciding if one should get their start money when passing start
     */
    public MoveToChanceCard (int destination, boolean getsStartMoney, String description){
        super(destination, description);
        this.getsStartMoney = getsStartMoney;
    }

    /**
     * Moves the player to the specified field
     *
     * @param player The player who drew the card
     */
    @Override
    public void action(Player player){

        if (getsStartMoney)
            GameLogic.movingPastStart(player, super.value);

        player.setPos(super.value);
        GuiHandler.getInstance().updatePlayerPos(player, Board.getInstance().getPlayers());
        Board.getInstance().getFields()[player.getPos()].landOnAction(player);
    }
}

