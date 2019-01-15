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

    private AuctionController(){
        board = Board.getInstance();
        guiHandler = GuiHandler.getInstance();
    }
    private static AuctionController instance;

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

    public void runCase(Player startingPlayer){


        //TODO skift til arrayList
        OwnableField field = (OwnableField) board.getFields()[startingPlayer.getPos()];
        int nextPlayer = 1, currentPlayer = 0;
        //Get list of buyers
        int buyerIndex = 0;
        for(int n = 0; n < board.getPlayers().length; n++){
            buyers.add(board.getPlayers()[n]);
        }

        int highestBid = field.getPrice();
        //Asks players if they want to buy the property
        do {
            int remainingBuyers = (buyers.size());
            if(buyers.size() == 1){
                guiHandler.giveMsg(buyers.get(0).getName() + " har købt " + field.getName());
                buyers.get(0).getAccount().changeScore(-highestBid);
                buyers.get(0).getOwnedFields().add(field);
                field.setOwner(buyers.get(0));
                return;
            }
                String answer = guiHandler.makeButtons("Vil de købe " + field.getName() + ", " + buyers.get(currentPlayer) + "?", "Ja", "Nej");
                if(answer.equals("Nej")) {
                    buyers.remove(currentPlayer);
                }else{
                    if(buyers.get(currentPlayer).getAccount().getScore() >= highestBid) {
                        highestBid = guiHandler.getUserInt("Hvad vil De byde på ejendommen?", highestBid, highestBid * 2);
                        currentPlayer = nextPlayer;
                        if((nextPlayer%remainingBuyers) == 0){
                            nextPlayer = 0;
                        }else {
                            nextPlayer++;
                        }
                    }else{
                        buyers.remove(currentPlayer);
                    }
                }


        }while(buyers.size() > 1);

    }
}
