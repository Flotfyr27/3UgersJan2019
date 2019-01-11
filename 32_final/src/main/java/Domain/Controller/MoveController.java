package Domain.Controller;
import Domain.GameElements.Entities.DiceTray;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Board;
import TechnicalServices.GameLogic.GameLogic;

public class MoveController {
    private static MoveController instance;
    private Board board;
    private DiceTray dice = new DiceTray();
    private MoveController(){
        board.getInstance();
    }

    /**
     * Creates this class as a singleton
     * @return
     */
    public static MoveController getInstance(){
        if(instance == null){
            instance = new MoveController();
            return instance;
        }
        else return instance;
    }

    /**
     * runCase method for MoveController. Moves player around the board and activates fields
     * @param p
     */
    public void runCase(Player p){
        int currentPos = p.getPos();
        dice.Roll();
        int dist = dice.getSum();
        GameLogic.movingPastStart(p,dist);
        currentPos = (currentPos + dist)%board.getFields().length;
        p.setPos(currentPos);

        board.getFields()[currentPos].landOnAction(p);
        if(!dice.IsDoubleValue()){
            p.setIsActive(false);
        }

    }

    public void runcase(Player p, int dist, boolean isDouble){
        int currentPos = p.getPos();
        GameLogic.movingPastStart(p,dist);
        currentPos = (currentPos + dist)%board.getFields().length;
        p.setPos(currentPos);

        board.getFields()[currentPos].landOnAction(p);
        if(!isDouble)
            p.setIsActive(false);
    }

    }



}
