package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import UI.GUI.GuiHandler;

public class AuctionController {
    Board board;
    GuiHandler guiHandler;
    public AuctionController(){
        board = Board.getInstance();
        guiHandler = GuiHandler.getInstance();
    }

    public void initAuction(Player startingPlayer){
        OwnableField field = (OwnableField) board.getFields()[startingPlayer.getPos()];
        int nextPlayer = 0, currentPlayer = 0;
        Player[] buyers = new Player[board.getPlayers().length-1];
        //Get list of buyers
        int buyerIndex = 0;
        for(int n = 0; n < board.getPlayers().length; n++){
            if(!board.getPlayers()[n].equals(startingPlayer)){
                buyers[buyerIndex] = board.getPlayers()[n];
                buyerIndex++;
            }
        }

    }
}
