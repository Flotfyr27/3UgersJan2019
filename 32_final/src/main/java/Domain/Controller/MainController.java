package Domain.Controller;

import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.GameLogic;
import UI.GUI.GuiHandler;


public class MainController {

   private GuiHandler guiHandler;
    private Player[] players;
    private int currentPlayerNum;
    private Player currentPlayer;
    private MoveController moveCon;
    /*private PrisonController prisonCon;
    private PawnController pawnCon;
    private TradeController tradeCon;*/

    public MainController(Player[] players){
        this.players = players;
        currentPlayerNum = 0;

        guiHandler = GuiHandler.getInstance();

        moveCon = MoveController.getInstance();
        /*prisonCon = new PrisonController.getInstance);
        pawnCon = new PawnController.getInstance;
        tradeCon = new TradeController.getInstance;*/
    }

    public void runCase(){
        while (!GameLogic.lastManStanding(players)){
            currentPlayer = players[currentPlayerNum];
            do{
                String choice;
                /*if (currentPlayer.getJailTime >=0) {
                    choice = guiHandler.makeButtons("vælg en handling",
                            "Slip fri", "Handel", "Pantsætning");
                    if (choice.equalsIgnoreCase("Slip fri"))
                        prisonCon.runCase();
                    if (choice.equalsIgnoreCase("Handel"))
                        tradeCon.runCase();
                    if (choice.equalsIgnoreCase("Pantsætning"))
                        pawnCon.runCase();
                } else {*/
                    choice = guiHandler.makeButtons("vælg en handling",
                            "Slå terninger", "Handel", "Pantsætning");
                    if (choice.equalsIgnoreCase("Slå terning"))
                        moveCon.runCase(currentPlayer);
                    if (choice.equalsIgnoreCase("Handel"))
                        ; //tradeCon.runCase();
                    if (choice.equalsIgnoreCase("Pantsætning"))
                        ; //pawnCon.runCase();
                //}

            }while (currentPlayer.getIsActive());

            currentPlayerNum++;
        }
    }

}
