import Domain.Controller.JailController;
import Domain.Controller.MainController;
import Domain.Controller.MoveController;
import Domain.GameElements.Board;
import UI.GUI.GuiHandler;

public class Main {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        GuiHandler guiHandler = GuiHandler.getInstance();
        guiHandler.initGuiFields(board.getFields());

        board.initBoard(guiHandler.getUserInt(3, 6));
        guiHandler.initGuiPlayers(board.getPlayers());

        MainController mainController = new MainController(board.getPlayers());
        MoveController.getInstance().initiate(board);
        JailController.getInstance();

        mainController.runCase();
    }
}
