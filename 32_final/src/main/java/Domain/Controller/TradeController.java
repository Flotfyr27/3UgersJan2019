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
        String targetPlayer, fieldString;
        OwnableField chosenField = null;
        Player chosenPlayer = null;
        String[] names = new String[board.getPlayers().length];
        String[] fieldNames = new String[playerTrading.getOwnedFields().size()];
        //Save all player names
        for(int n = 0; n < board.getPlayers().length; n++){
           names[n] = board.getPlayerAtIndex(n).getName();
        }
        //Choose a player based on user selected name
        targetPlayer = guiHandler.makeButtons("Select a player to trade with", names);
        for(int n = 0; n < board.getPlayers().length; n++){
            if(targetPlayer.equals(board.getPlayerAtIndex(n).getName())){
                chosenPlayer = board.getPlayerAtIndex(n);
            }
        }
        //Save all field names
        for(int n = 0; n < playerTrading.getOwnedFields().size(); n++){
            fieldNames[n] = playerTrading.getOwnedFields().get(n).getName();
        }
        //Select a field to trade based on user input
        fieldString = guiHandler.makeButtons("Select field to trade", fieldNames);
        for(int n = 0; n < playerTrading.getOwnedFields().size(); n++){
            if(fieldString.equals(fieldNames[n])){
                chosenField = playerTrading.getOwnedFields().get(n);
            }
        }
        //Discuss price
        int salesPrice = 0;
        String confirmationOfSale = "";
        do {
            salesPrice = guiHandler.getUserInt("Indtast salgsprisen: ");
            guiHandler.giveMsg(playerTrading.getName() + " sælger " + chosenField.getName() + " til " + chosenPlayer.getName() + " for kr. " + salesPrice + ".");
            confirmationOfSale = guiHandler.makeButtons("Er dette korrekt?", "Ja", "Nej");
        }while(confirmationOfSale.equals("Nej"));

        //Take money from the buying player and add to selling player
        if(chosenPlayer.getAccount().getScore() >= chosenField.getPrice()) {
            chosenPlayer.getAccount().changeScore(-chosenField.getPrice());
            playerTrading.getAccount().changeScore(chosenField.getPrice());
        }else{
            return;
        }



    }
}
