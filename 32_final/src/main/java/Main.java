import Domain.Controller.JailController;
import Domain.Controller.MainController;
import Domain.Controller.MoveController;
import Domain.GameElements.Board;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Fields.Ownable.OwnableField;
import UI.GUI.GuiHandler;

public class Main {
    /**
     * The method forstarting up the game. this is the first method to run and will set up the most important elements of the game in the correct order.
     * @param args The standard argument for the public static void main class.
     */
    public static void main(String[] args) {
        Board board = Board.getInstance();
        GuiHandler guiHandler = GuiHandler.getInstance();
        guiHandler.initGuiFields(board.getFields());

        board.initBoard(guiHandler.getUserInt("Vælg mellem 3 og 6 spillere", 3, 6));
        guiHandler.initGuiPlayers(board.getPlayers());

        MainController mainController = new MainController(board.getPlayers());
        MoveController.getInstance().initiate(board);
        JailController.getInstance();

        mainController.runCase();
    }
}
