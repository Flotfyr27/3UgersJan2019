package Domain.Controller;
import Domain.GameElements.Entities.Die;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Board;

public class MoveController {
    private static MoveController instance;
    private final int boardLength = 40;
    private Board board;
    public void movePlayer(Player p, int dist){
        int currentPos = p.getPos();
        if((currentPos + dist) >= boardLength){
            p.getAccount().changeScore(4000);
        }
        currentPos = (currentPos + dist)%boardLength;
        p.setPos(currentPos);

        board.getFields()[currentPos].landOnAction();
    }

    public static MoveController getInstance(){
        if(instance == null){
            instance = new MoveController();
            return instance;
        }
        else return instance;
    }

}
