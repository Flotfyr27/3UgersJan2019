package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import UI.GUI.GuiHandler;

public class TradeController {
    private GuiHandler guiHandler;
    private Board board;
    public TradeController(){
        guiHandler = GuiHandler.getInstance();
        board = Board.getInstance();
    }

    public void tradeWithPlayer(Player playerTrading){
        String chosenPlayer, fieldString;
        OwnableField chosenField;
        String[] names = new String[board.getPlayers().length];
        String[] fieldNames = new String[playerTrading.getOwnedFields().size()];
        for(int n = 0; n < board.getPlayers().length; n++){
           names[n] = board.getPlayerAtIndex(n).getName();
        }

        chosenPlayer = guiHandler.makeButtons("Select a player to trade with", names);
        for(int n = 0; n < playerTrading.getOwnedFields().size(); n++){
            fieldNames[n] = playerTrading.getOwnedFields().get(n).getName();
        }
        fieldString = guiHandler.makeButtons("Select field to trade", fieldNames);
        //NOT DONE YET PLS FIX
    }
}
