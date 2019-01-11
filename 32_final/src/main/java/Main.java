import Domain.Controller.MainController;
import Domain.Controller.MoveController;
import Domain.GameElements.Board;
import UI.GUI.GuiHandler;

public class Main {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        GuiHandler guiHandler = GuiHandler.getInstance().instantiateGui(board.getFields());

        board.initPlayers(guiHandler.getNumberOfPlayers(3, 6));

        MainController mainController = new MainController(board.getPlayers());
        MoveController moveController = MoveController.getInstance();
        //JailController jailController = JailControllor.getInstance();

        mainController.runCase();
    }
}
