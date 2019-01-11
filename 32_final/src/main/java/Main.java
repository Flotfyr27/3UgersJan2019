import Domain.Controller.MainController;
import Domain.Controller.MoveController;
import Domain.GameElements.Board;
import UI.GUI.GuiHandler;

public class Main {
    public static void main(String[] args) {
        GuiHandler guiHandler = GuiHandler.getInstance();
        Board board = Board.getInstance();

        board.initPlayers(guiHandler.getNumberOfPlayers(3, 6));

        MainController mainController = new MainController(board.getPlayers());
        MoveController moveController = MoveController.getInstance();
        JailController jailController = JailControllor.getInstance();
    }
}
