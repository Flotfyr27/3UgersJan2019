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

        buyerIndex = 0;
        boolean running = true;
        int highestBid = field.getPrice();
        int remainingBuyers = (buyers.length-1);
        //Asks players if they want to buy the property
        do {
            if(!buyers[buyerIndex].equals(null)) {
                String answer = guiHandler.makeButtons(buyers[buyerIndex].getName() + " vil De købe ejendommen?", "Ja", "Nej");
                //If a players responds with no, they are nullified.
                if (answer.equals("Nej")) {
                    buyers[buyerIndex] = null;
                 //If a player responds with yes, they place a bid.
                } else {
                    highestBid = guiHandler.getUserInt("Indtast bud på ejendom ", highestBid, 30000);
                    buyerIndex++;
                }
                //If players are nullified, index is incremented
            } else {
                if(buyerIndex == buyers.length){
                    buyerIndex = 0;
                }else{
                    buyerIndex++;
                }
            }
            for(int n = 0; n < (buyers.length-1); n++){
                if(buyers[n].equals(null)){
                    remainingBuyers--;
                }
            }
            if(remainingBuyers == 1){
                running = false;
            }
        }while(running);

    }
}
