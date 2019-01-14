package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;

public class AuctionController {
    Board board;
    public AuctionController(){
        board = Board.getInstance();
    }

    public void initAuction(){}
    private int getPlayerIndex(Player player){
        int index = 0;
        for(int n = 0; n < board.getPlayers().length; n++){
            if(board.getPlayerAtIndex(n).equals(player)){
                index = n;
                break;
            }
        }
        return index;
    }
}
