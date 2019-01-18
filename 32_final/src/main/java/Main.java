import Domain.Controller.JailController;
import Domain.Controller.MainController;
import Domain.Controller.MoveController;
import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
import TechnicalServices.DiceTrayRigged;
import UI.GUI.GuiHandler;
import gui_fields.GUI_Player;

public class Main {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        GuiHandler guiHandler = GuiHandler.getInstance();
        guiHandler.initGuiFields(board.getFields());
        boolean isPresentation = true;

        String valg = guiHandler.makeButtons("Vil du spille spillet eller køre præsentationen?", "Spil spillet", "Præsentation");

        if (valg.equalsIgnoreCase("Spil spillet")) {
            isPresentation = false;
        }
        if (!isPresentation) {

            standardMode(board, guiHandler);
        } else {
            presentationMode(board, guiHandler);

        }


    }

    private static void standardMode(Board board, GuiHandler guiHandler) {
        board.initBoard(guiHandler.getUserInt("Vælg mellem 3 og 6 spillere", 3, 6), false);
        guiHandler.initGuiPlayers(board.getPlayers());

        MainController mainController = new MainController(board.getPlayers());
        MoveController.getInstance().initiate(board);
        JailController.getInstance();

        mainController.runCase();
    }

    private static void presentationMode(Board board, GuiHandler guiHandler){
        board.initBoard(4, true);
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

        switch(MoveController.getInstance().getRoundNr()){
            case 1:
                DiceTrayRigged.roll(2,3);
        }




        //guiHandler.updateGui(board.getPlayers()[0], board.getPlayers(), board.getFields());
       // guiHandler.teleportPlayer(board.getPlayers()[0].getPos(), board.getPlayers()[0],board.getPlayers());



        MainController mainController = new MainController(board.getPlayers());
        MoveController.getInstance().initiate(board);
        JailController.getInstance();

        mainController.runCase();

    }

    //Method to set a players starting position
    private static void setPlayerStartPosition(Board board, GuiHandler guiHandler, int playerNum, int pPos){
        board.getPlayers()[playerNum].setPos(pPos);
        guiHandler.updateGui(board.getPlayers()[playerNum], board.getPlayers(), board.getFields());
    }

    //Method to assign ownable fields to a player
    private static void setFieldOwner(Board board, int playerNum, int FieldNum){
        board.getPlayers()[playerNum].getOwnedFields().add((OwnableField)board.getFields()[FieldNum]);
        ((OwnableField)board.getFields()[FieldNum]).setOwner(board.getPlayers()[playerNum]);

    }
}
