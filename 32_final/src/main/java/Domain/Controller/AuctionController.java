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
    private Player highestBidder = null;

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

    public void runCase(Player startingPlayer){


        //TODO skift til arrayList
        buyers = new ArrayList<Player>();
        OwnableField field = (OwnableField) board.getFields()[startingPlayer.getPos()];
        int nextPlayer = 1, currentPlayer = 0;
        //Get list of buyers
        for(int n = 0; n < board.getPlayers().length; n++){
            if(!board.getPlayers()[n].equals(startingPlayer)){
                buyers.add(board.getPlayers()[n]);
            }
        }

        int highestBid = field.getPrice();
        //Asks players if they want to buy the property
        boolean isFirstRound = true;
        int rounds = 0;
        do {

                String answer = guiHandler.makeButtons("Vil de købe " + field.getName() + ", " + buyers.get(currentPlayer).getName() + "?", "Ja", "Nej");
                if(answer.equals("Nej")) {
                    buyers.remove(currentPlayer);

                    if(buyers.size() <= currentPlayer){
                        currentPlayer = 0;
                        nextPlayer = currentPlayer + 1;
                    }
                }else{
                    if(buyers.get(currentPlayer).getAccount().getScore() >= highestBid) {
                        if(highestBidder == null){
                        highestBid = guiHandler.getUserInt("Hvad vil De byde på ejendommen? Højeste bud: " + highestBid, highestBid, buyers.get(currentPlayer).getAccount().getScore());
                        }else{
                            highestBid = guiHandler.getUserInt("Hvad vil De byde på ejendommen? Højeste bud: " + highestBid + " (" + highestBidder.getName() + ")", highestBid+50, buyers.get(currentPlayer).getAccount().getScore());
                        }
                        highestBidder = buyers.get(currentPlayer);
                        currentPlayer = nextPlayer++;
                        if((nextPlayer%buyers.size()) == 0){
                            nextPlayer = 0;
                        }
                    }else{
                        buyers.remove(currentPlayer);
                    }
                }
            if(buyers.size() == 1 && !isFirstRound){
                guiHandler.giveMsg(buyers.get(0).getName() + " har købt " + field.getName() + " for kr. " + highestBid);
                buyers.get(0).getAccount().changeScore(-highestBid);
                buyers.get(0).getOwnedFields().add(field);
                field.setOwner(buyers.get(0));
                return;
            } else if (buyers.size() == 1 && isFirstRound) {
                Player lastBuyer = buyers.get(0);
                String answer2 = guiHandler.makeButtons(lastBuyer.getName() + " vil De købe " + field.getName() + " for kr " + highestBid + "?", "Ja", "Nej");
                if(answer2.equals("Ja")){
                    if(lastBuyer.getAccount().getScore() >= highestBid){
                        lastBuyer.getAccount().changeScore(-highestBid);
                        lastBuyer.getOwnedFields().add(field);
                        field.setOwner(lastBuyer);
                        guiHandler.giveMsg(lastBuyer.getName() + " har købt " + field.getName() + " for kr. " + highestBid);
                        return;
                    }

                }else{
                    return;
                }
            }
            if(rounds >= board.getPlayers().length-1){
                isFirstRound = false;
            }else{
                rounds++;
            }

        }while(buyers.size() >= 1);

    }
}
