package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;

public class AuctionController {
    Board board;
    GuiHandler guiHandler;
    public AuctionController(){
        board = Board.getInstance();
        guiHandler = GuiHandler.getInstance();
    }

    public void initAuction(Player startingPlayer){
        int nextPlayer = 0, currentPlayer = 0;
        int indexOfStartingPlayer = 0;
        //Get location of starting player
        for(int n = 0; n < board.getPlayers().length; n++){
            if(board.getPlayers()[n].equals(startingPlayer)){
                indexOfStartingPlayer = n;
            }
        }
        //Select other players for first round of bidding.
        if(indexOfStartingPlayer == (board.getPlayers().length-1)){
            nextPlayer = 0;
        }else{
            nextPlayer = ++indexOfStartingPlayer;
        }

    }
}
