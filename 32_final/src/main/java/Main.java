import Domain.Controller.JailController;
import Domain.Controller.MainController;
import Domain.Controller.MoveController;
import Domain.GameElements.Board;
import Domain.GameElements.Fields.Ownable.OwnableField;

import UI.GUI.GuiHandler;

public class Main {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        GuiHandler guiHandler = GuiHandler.getInstance();
        guiHandler.initGuiFields(board.getFields());

        board.initBoard(guiHandler.getUserInt("Vælg mellem 3 og 6 spillere", 3, 6));
        guiHandler.initGuiPlayers(board.getPlayers());


        //Sets accountScore for all dummy players
        for (int i = 0; i < board.getPlayers().length; i++) {
            board.getPlayers()[i].getAccount().setScore(10000);
        }
        guiHandler.updateBalance(board.getPlayers());

        setPlayerStartPosition(board, guiHandler, 0, 13);
        setPlayerStartPosition(board, guiHandler, 1, 18);
        setPlayerStartPosition(board, guiHandler, 2, 20);
        setPlayerStartPosition(board, guiHandler, 3, 1);

        setFieldOwner(board, 1, 14);
        setFieldOwner(board, 0, 19);


        MainController mainController = new MainController(board.getPlayers());
        MoveController.getInstance().initiatePresentation(board);
        JailController.getInstance();
        mainController.runCase();
    }
}
