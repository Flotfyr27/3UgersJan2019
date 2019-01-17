package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import UI.GUI.GuiHandler;

import java.util.ArrayList;

public class AuctionController {
    private Board board;
    private GuiHandler guiHandler;
    private ArrayList<Player> buyers;
    private static AuctionController instance;

    private OwnableField chosenField;
    private Player currentPlayer;
    private int playerIndex = 0;


    private AuctionController(){
        board = Board.getInstance();
        guiHandler = GuiHandler.getInstance();
    }

    /**
     * Creates this class as a singleton
     * @return
     */
    public static AuctionController getInstance(){
        if(instance == null){
            instance = new AuctionController();
            return instance;
        }
        else return instance;
    }



    public void runCase(Player startingPlayer) {
        //Get list of buyers
        getListOfBuyers(startingPlayer);
        //Get field to be auctioned off
        getFieldToBeAuctioned(startingPlayer);
        //Initializes the current player
        initCurrentPlayer();

    }

    private void initCurrentPlayer(){
        currentPlayer = buyers.get(0);
    }

    private Player getNextPlayer(){
        Player nextPlayer;
        if(currentPlayer == buyers.get(buyers.size())){
            nextPlayer = buyers.get(0);
        }else{
            nextPlayer = buyers.get(playerIndex);
        }
        return nextPlayer;
    }
    private void getFieldToBeAuctioned(Player startingPlayer) {
        chosenField = (OwnableField)board.getFields()[startingPlayer.getPos()];
    }

    private void getListOfBuyers(Player startingPlayer) {
        buyers = new ArrayList<Player>();
        for (int n = 0; n < board.getPlayers().length; n++) {
            if (!board.getPlayers()[n].equals(startingPlayer)) {
                buyers.add(board.getPlayers()[n]);
            }
        }
    }
}
