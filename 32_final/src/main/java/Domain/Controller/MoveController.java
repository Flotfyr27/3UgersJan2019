package Domain.Controller;
import Domain.GameElements.Entities.DiceTray;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Board;
import TechnicalServices.DiceTrayRigged;
import TechnicalServices.GameLogic.GameLogic;
import UI.GUI.GuiHandler;

public class MoveController {
    private Board board;
    private DiceTray dice;
    private GuiHandler guiHandler;
    private int roundNr = 0;


    private static MoveController instance;

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
     * Constructor
     */
    private MoveController(){
        guiHandler = GuiHandler.getInstance();
    }

    public MoveController initiate(Board board){
        this.board = board;
        this.dice = board.getDiceTray();
        return this;
    }

    /**
     * runCase method for MoveController. Moves player around the board and activates fields
     * @param p
     */
    public void runCase(Player p){
        int currentPos = p.getPos();
        dice.Roll();
        guiHandler.showDice(dice.getValue1(), dice.getValue2());
        int dist = dice.getSum();

        GameLogic.movingPastStart(p,p.getPos()+dist);
        currentPos = (currentPos + dist)%board.getFields().length;
        p.setPos(currentPos);
        guiHandler.updateGui(p, board.getPlayers(), board.getFields());

        board.getFields()[currentPos].landOnAction(p);
        if(!dice.IsDoubleValue()){
            p.setIsActive(false);
        }
        guiHandler.updateGui(p, board.getPlayers(), board.getFields());

        //For use in PresentationMode
        roundNr++;
    }

    public void runCase(Player p, int dist, boolean isDouble){
        int currentPos = p.getPos();
        GameLogic.movingPastStart(p,p.getPos()+dist);
        currentPos = (currentPos + dist)%board.getFields().length;
        p.setPos(currentPos);
        guiHandler.updateGui(p, board.getPlayers(), board.getFields());

        board.getFields()[currentPos].landOnAction(p);
        if(!dice.IsDoubleValue()){
            p.setIsActive(false);
        }
        guiHandler.updateGui(p, board.getPlayers(), board.getFields());

        //For use in presentationMode
        roundNr++;
    }

    public DiceTray getDiceTray() {
        return dice;
    }

    public int getRoundNr(){
        return roundNr;
    }

}




