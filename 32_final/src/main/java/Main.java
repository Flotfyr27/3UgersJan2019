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

        board.initBoard(guiHandler.getUserInt("Vælg mellem 3 og 6 spillere", 3, 6));
        guiHandler.initGuiPlayers(board.getPlayers());

        MainController mainController = new MainController(board.getPlayers());
        MoveController.getInstance().initiate(board);
        JailController.getInstance();

     /* Let a player start with some fields for testing TODO delete it*/
        board.getFields()[1].landOnAction(board.getPlayers()[0]);
        board.getFields()[3].landOnAction(board.getPlayers()[0]);
        board.getFields()[6].landOnAction(board.getPlayers()[0]);
        board.getFields()[8].landOnAction(board.getPlayers()[0]);
        board.getFields()[9].landOnAction(board.getPlayers()[0]);
        board.getFields()[11].landOnAction(board.getPlayers()[0]);
        board.getFields()[13].landOnAction(board.getPlayers()[0]);
        board.getFields()[14].landOnAction(board.getPlayers()[0]);
        board.getFields()[16].landOnAction(board.getPlayers()[0]);
        board.getFields()[18].landOnAction(board.getPlayers()[0]);
        board.getFields()[19].landOnAction(board.getPlayers()[0]);
        board.getPlayers()[0].getAccount().changeScore(3000000);
        mainController.runCase();
    }
}
