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
    private Player currentPlayer, playerWithHighestBid = null;
    private int playerIndex = 0, highestBid = 0;


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
        //Loop going through all players choosing to bid or not for the initial bidding round
        runFirstRound();
        //Find the buyer of the field, go through loop until a single buyer is found.


    }

    private void runFirstRound() {
        boolean wantsToBuy[] = new boolean[buyers.size()];


            for(int n = 0; n < buyers.size()-1; n++){
                String answerFirstRound = guiHandler.makeButtons(currentPlayer.getName() + " vil De byde på " + chosenField.getName() + "?", "Ja", "Nej");
                if(answerFirstRound.equals("Ja")){
                    wantsToBuy[n] = true;
                    if(playerWithHighestBid == null){
                        highestBid = guiHandler.getUserInt(currentPlayer.getName() + " hvor meget ønsker De at byde på " + chosenField.getName() + "? (Mindst " +highestBid + ")", highestBid, currentPlayer.getAccount().getScore());
                    }else if(playerWithHighestBid != null){
                        highestBid = guiHandler.getUserInt(currentPlayer.getName() + " hvor meget ønsker De at byde på " + chosenField.getName() + "? (Højeste bud: kr. " + highestBid + " af " + playerWithHighestBid.getName(), highestBid, currentPlayer.getAccount().getScore());
                        playerWithHighestBid = currentPlayer;
                    }

                }else if(answerFirstRound.equals("Nej")){
                    wantsToBuy[n] = false;
                }
                updateCurrentPlayer();
            }
            for(int n = 0; n < wantsToBuy.length; n++){
                if(!wantsToBuy[n]){
                    buyers.remove(n);
                }
            }

    }


    private void initCurrentPlayer(){
        currentPlayer = buyers.get(0);
    }

    private void updateCurrentPlayer(){
        currentPlayer = getNextPlayer();
    }

    private Player getNextPlayer(){
        Player nextPlayer;
        if(currentPlayer == buyers.get(buyers.size())){
            nextPlayer = buyers.get(0);
        }else{
            playerIndex++;
            nextPlayer = buyers.get(playerIndex);
        }
        return nextPlayer;
    }
    private void getFieldToBeAuctioned(Player startingPlayer) {
        chosenField = (OwnableField)board.getFields()[startingPlayer.getPos()];
        highestBid = chosenField.getPrice();
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
