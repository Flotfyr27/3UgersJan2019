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

    public void runCase(Player playerTrading){
        String targetPlayer, fieldString;
        OwnableField chosenField = null;
        Player chosenPlayer = null;
        String[] names = new String[board.getPlayers().length - 1];
        String[] fieldNames = new String[playerTrading.getOwnedFields().size()];
        int i = 0;

        //Save all player names
        for(int n = 0; n < board.getPlayers().length; n++){
            if (!playerTrading.equals(board.getPlayers()[n]))
                names[i++] = board.getPlayerAtIndex(n).getName();
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
        if (fieldNames.length > 0) {
            fieldString = guiHandler.makeButtons("Select field to trade", fieldNames);
            for (int n = 0; n < playerTrading.getOwnedFields().size(); n++) {
                if (fieldString.equals(fieldNames[n])) {
                    chosenField = playerTrading.getOwnedFields().get(n);
                }
            }
        } else {
            guiHandler.makeButtons("Du har ingen grunde at handle med", "Ok");
            return;
        }


        //Discuss price until a price is found or purchase is aborted
        int salesPrice = 0;
        String confirmationOfSale = "";
        do {
            salesPrice = guiHandler.getUserInt("Indtast salgsprisen: ");

            confirmationOfSale = guiHandler.makeButtons(playerTrading.getName() + " sælger " +
                    chosenField.getName() + " til " + chosenPlayer.getName() + " for kr. " + salesPrice +
                    "." + "Er dette korrekt?", "Ja", "Nej", "Afbryd køb");
            if(confirmationOfSale.equals("Afbryd køb")){
                guiHandler.giveMsg("Køb afbrudt!");
                return;
            }
        }while(confirmationOfSale.equals("Nej"));

        //Take money from the buying player and add to selling player
        if(chosenPlayer.getAccount().getScore() >= salesPrice) {
            chosenPlayer.getAccount().changeScore(-salesPrice);
            playerTrading.getAccount().changeScore(salesPrice);
            playerTrading.getOwnedFields().remove(chosenField);
            chosenPlayer.getOwnedFields().add(chosenField);
            chosenField.setOwner(chosenPlayer);
        }else{
            guiHandler.giveMsg(chosenPlayer.getName() + " har ikke råd til denne handel.");
            return;
        }
        guiHandler.giveMsg(chosenPlayer.getName() + " har købt " + chosenField.getName() + " for kr. " + salesPrice + " af " + playerTrading.getName());
    }
}
