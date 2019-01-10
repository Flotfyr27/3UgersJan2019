package Domain.Controller;
import Domain.GameElements.Entities.Die;
import Domain.GameElements.Entities.Player;


public class MoveController {
    private final int boardLength = 40;
    public void movePlayer(Player p, int dist){
        int currentPos = p.getPos();
        if((currentPos + dist) >= boardLength){
            p.getAccount().changeScore(4000);
        }
        currentPos = (currentPos + dist)%boardLength;
        p.setPos(currentPos);
    }
}
