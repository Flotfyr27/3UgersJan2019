import Domain.Controller.MainController;
import Domain.GameElements.Board;
import UI.GUI.GuiHandler;

public class Main {
    public static void main(String[] args) {
        GuiHandler guiHandler = GuiHandler.getInstance();
        Board board;


        guiHandler.getNumberOfPlayers(3, 6);
        MainController mainController = new MainController();

    }
}
