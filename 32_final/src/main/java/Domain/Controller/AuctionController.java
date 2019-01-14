package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;

public class AuctionController {
    Board board;
    public AuctionController(){
        board = Board.getInstance();
    }

    public void initAuction(){}
}
