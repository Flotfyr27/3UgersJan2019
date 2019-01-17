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
        finishAuction();
        //A single buyer is now present and must buy the property
        sellPropertyToPlayer();

    }

    private void sellPropertyToPlayer() {
        if(buyers.size() == 1) {
            initCurrentPlayer();
            currentPlayer.getAccount().changeScore(-highestBid);
            currentPlayer.getOwnedFields().add(chosenField);
            chosenField.setOwner(currentPlayer);
            guiHandler.giveMsg(currentPlayer.getName() + " har købt " + chosenField.getName() + " for kr. " + highestBid);
        }
    }

    private void finishAuction() {
        while(buyers.size() > 1){
            initCurrentPlayer();

            for(int n = 0; n < buyers.size(); n++){
                boolean wantsToBuy[] = new boolean[buyers.size()];
                for (int i = 0; i < wantsToBuy.length; i++) {
                    wantsToBuy[i] = true;
                }

                String answerEndRounds = guiHandler.makeButtons(currentPlayer.getName() + " vil De stadig byde på " + chosenField.getName() + "?", "Ja", "Nej");
                if(answerEndRounds.equals("Ja")){
                    if (buyers.size() > 1) {
                        highestBid = guiHandler.getUserInt(currentPlayer.getName() + " hvor meget ønsker De at byde på " + chosenField.getName() + "? (Højeste bud: kr. " + highestBid + " af " + playerWithHighestBid.getName() + ")", highestBid + 50, currentPlayer.getAccount().getScore());
                        playerWithHighestBid = currentPlayer;
                        wantsToBuy[n] = true;
                    }
                }else if(answerEndRounds.equals("Nej")){
                    wantsToBuy[n] = false;
                }
                updateCurrentPlayer();
                removeBuyers(wantsToBuy);
            }

        }
    }

    private void runFirstRound() {
        boolean wantsToBuy[] = new boolean[buyers.size()];


            for(int n = 0; n < buyers.size(); n++){
                String answerFirstRound = guiHandler.makeButtons(currentPlayer.getName() + " vil De byde på " + chosenField.getName() + "?", "Ja", "Nej");
                checkAnswer(wantsToBuy, n, answerFirstRound);
                updateCurrentPlayer();
            }

            removeBuyers(wantsToBuy);
    }

    private void removeBuyers(boolean[] wantsToBuy) {
        int count = 0;
        for (int i = 0; i < wantsToBuy.length; i++){
            if (!wantsToBuy[i]){
                System.out.println("Removed " + buyers.get(i - count).getName());
                buyers.remove(i - count++);
            }
        }

    }

    private void checkAnswer(boolean[] wantsToBuy, int n, String answerFirstRound) {
        if(answerFirstRound.equals("Ja")){
            wantsToBuy[n] = true;
            if(playerWithHighestBid == null){
                highestBid = guiHandler.getUserInt(currentPlayer.getName() + " hvor meget ønsker De at byde på " + chosenField.getName() + "? (Mindst " +highestBid + ")", highestBid, currentPlayer.getAccount().getScore());
                playerWithHighestBid = currentPlayer;
            }else if(playerWithHighestBid != null){
                highestBid = guiHandler.getUserInt(currentPlayer.getName() + " hvor meget ønsker De at byde på " + chosenField.getName() + "? (Højeste bud: kr. " + highestBid + " af " + playerWithHighestBid.getName(), highestBid+50, currentPlayer.getAccount().getScore());
                playerWithHighestBid = currentPlayer;
                System.out.println("Highest bidder " + playerWithHighestBid.getName());
            }

        }else if(answerFirstRound.equals("Nej")){
            wantsToBuy[n] = false;
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
        playerIndex = ++playerIndex % buyers.size();
        nextPlayer = buyers.get(playerIndex);
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
