package Domain.GameElements.Entities.Chancecard;
import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Field;
import TechnicalServices.GameLogic.GameLogic;
import UI.GUI.GuiHandler;
import com.oracle.deploy.update.Updater;
import com.sun.deploy.config.AutoUpdater;

public class MoveToChanceCard extends MoveChanceCard {
    private boolean getsStartMoney;

    /**
     * Constructor. The destination is the fields place in the Field array Board.
     * The description is the text presented to the player.
     *
     * @param destination
     * @param description
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
     * @param destination
     * @param description
     * @param getsStartMoney
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

