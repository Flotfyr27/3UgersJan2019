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
                        sellPropertyToPlayer();
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
                            highestBid = guiHandler.getUserInt(currentPlayer.getName() + " De skal mindst byde kr. " + highestBid, highestBid, currentPlayer.getAccount().getScore());
                            playerWithHighestBid = currentPlayer;
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

    private void sellPropertyToPlayer() {
            currentPlayer.getAccount().changeScore(-highestBid);
            currentPlayer.getOwnedFields().add(chosenField);
            chosenField.setOwner(currentPlayer);
            guiHandler.giveMsg(currentPlayer.getName() + " har købt " + chosenField.getName() + " for kr. " + highestBid);
    }


//TODO evt check dis out https://stackoverflow.com/questions/10738634/delete-data-from-arraylist-with-a-for-loop

    private void setUpAuction(Player startingPlayer) {
        chosenField = (OwnableField)board.getFields()[startingPlayer.getPos()];
        highestBid = chosenField.getPrice();
        playerWithHighestBid = null;
        currentPlayer = null;
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
