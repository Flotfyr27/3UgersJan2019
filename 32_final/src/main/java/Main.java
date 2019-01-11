import Domain.Controller.MainController;
import UI.GUI.GuiHandler;

public class Main {
    public static void main(String[] args) {
        GuiHandler guiHandler = GuiHandler.getInstance();


        guiHandler.getNumberOfPlayers(3, 6);
        MainController mainController = new MainController();

    }
}
