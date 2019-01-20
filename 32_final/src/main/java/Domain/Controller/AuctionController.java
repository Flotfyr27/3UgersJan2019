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
    private int highestBid = 0;

    /**
     * Constructor for the singleton of AuctionController
     */
    private AuctionController(){
        board = Board.getInstance();
        guiHandler = GuiHandler.getInstance();
    }

    /**
     * Creates this class as a singleton
     * @return An instance of AuctionController
     */
    public static AuctionController getInstance(){
        if(instance == null){
            instance = new AuctionController();
            return instance;
        }
        else return instance;
    }


    /**
     * This method is the main run method of the AuctionController
     * The method will go through an ArrayList of potential buyers of a field
     * It will do so in a few steps
     * 1. Ask a player if they want to bid on the property
     * 2. Ask the player to enter an amount if yes and remove the player if no.
     * 3. Repeat step 2 until only one buyer remains
     * 4. If possible sell the property to last buyer (In case at least one said yes)
     * @param startingPlayer This is the player who initially landed on the field
     */
    public void runCase(Player startingPlayer) {
        //Get field to be auctioned off
        setUpAuction(startingPlayer);
        //Get list of buyers
        getListOfBuyers(startingPlayer);
        //Initializes
        while(buyers.size() > 1) {
            for (int n = buyers.size() - 1; n >= 0; n--) {
                currentPlayer = buyers.get(n);
                String answer;
                //When only 1 remains and all others before have said no
                if (buyers.size() == 1 && playerWithHighestBid == null) {
                    answer = guiHandler.makeButtons(currentPlayer.getName() + " vil De købe " + chosenField.getName() + " for kr. " + highestBid + "?", "Ja", "Nej");
                    if (answer.equals("Ja")) {
                        if (currentPlayer.getAccount().canBuy(-highestBid))
                            sellPropertyToPlayer();
                        else {
                            buyers.remove(n);
                            guiHandler.giveMsg("Du har ikke råd til at købe denne grund. Ingen købte grunden");
                        }
                    } else if (answer.equals("Nej")) {
                        buyers.remove(n);
                        guiHandler.giveMsg("Ingen købte skødet");
                        return;
                    }

                } else if (buyers.size() > 1) { //This part is where everything but the "all no scenario" goes on
                    if (playerWithHighestBid == null) {
                        //This part focuses on the first bid for everyone
                        answer = guiHandler.makeButtons(currentPlayer.getName() + " vil De byde på " + chosenField.getName() + "?", "Ja", "Nej");
                        if (answer.equals("Ja")) {
                            if(buyers.get(n).getAccount().canBuy(highestBid)){
                                highestBid = guiHandler.getUserInt(currentPlayer.getName() + " De skal mindst byde kr. " + highestBid, highestBid, currentPlayer.getAccount().getScore());
                                playerWithHighestBid = currentPlayer;
                            } else{
                                guiHandler.giveMsg("Du har ikke råd til at købe denne grund");
                                buyers.remove(n);
                            }
                        } else if (answer.equals("Nej")) {
                            System.out.println("Removed " + currentPlayer.getName());
                            buyers.remove(n);
                        }
                        //This part focuses on what happens after the first player to bid has done so
                    } else if (playerWithHighestBid != null) {
                        answer = guiHandler.makeButtons(currentPlayer.getName() + " højeste bud er kr. " + highestBid
                                + " budt af " + playerWithHighestBid.getName() + ". Ønsker De at byde over?", "Ja", "Nej");
                        if (answer.equals("Ja")) {
                            highestBid = guiHandler.getUserInt("Indtast bud. (Højeste bud kr. " + highestBid + " af "
                                    + playerWithHighestBid.getName() + ")", highestBid + 50, currentPlayer.getAccount().getScore());
                            playerWithHighestBid = currentPlayer;
                        } else if (answer.equals("Nej")) {
                            System.out.println("Removed " + currentPlayer.getName());
                            buyers.remove(n);
                        }
                    }

                }
            }
        }
        currentPlayer = buyers.get(0);
        if (buyers.size() == 1 && playerWithHighestBid != null) {
            currentPlayer.getAccount().changeScore(-highestBid);
            currentPlayer.getOwnedFields().add(chosenField);
            chosenField.setOwner(currentPlayer);
            guiHandler.giveMsg(currentPlayer.getName() + " har købt " + chosenField.getName() + " for kr. " + highestBid + "!");
            buyers.remove(0);
            return;
        }

    }

    /**
     * This method sells the property up for auction and adds it to a players inventory
     */
    private void sellPropertyToPlayer() {
            currentPlayer.getAccount().changeScore(-highestBid);
            currentPlayer.getOwnedFields().add(chosenField);
            chosenField.setOwner(currentPlayer);
            guiHandler.giveMsg(currentPlayer.getName() + " har købt " + chosenField.getName() + " for kr. " + highestBid);
    }

    /**
     * This method sets up the auction by getting the field, setting the current player and who has the highest bid
     * @param startingPlayer The player who first landed on the field
     */
   private void setUpAuction(Player startingPlayer) {
        chosenField = (OwnableField)board.getFields()[startingPlayer.getPos()];
        highestBid = chosenField.getPrice();
        playerWithHighestBid = null;
        currentPlayer = null;
    }

    /**
     * This method creates an ArrayList of buyers to be used in runCase()-method
     * @param startingPlayer This player will not be added to the list of buyers, since they said no in the first place.
     */
    private void getListOfBuyers(Player startingPlayer) {
        buyers = new ArrayList<Player>();
        for (int n = 0; n < board.getPlayers().length; n++) {
            if (!board.getPlayers()[n].equals(startingPlayer)) {
                buyers.add(board.getPlayers()[n]);
            }
        }
    }
}
