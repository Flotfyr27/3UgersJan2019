package Domain.Controller;

import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.GameLogic;
import UI.GUI.GuiHandler;


public class MainController {

    protected GuiHandler guiHandler;
    protected Player[] players;
    protected int currentPlayerNum;
    protected Player currentPlayer;
    private MoveController moveCon;
    private PrisonController prisonCon;
    private PawnController pawnCon;
    private TradeController tradeCon;

    public void runCase(){
        while (!GameLogic.lastManStanding()){
            currentPlayer = players[currentPlayerNum];
            do{
                String choice;
                if (currentPlayer.getIsJaieled) {
                    choice = guiHandler.makeButtons("vælg en handling",
                            "Slip fri", "Handel", "Pantsætning");
                    if (choice.equalsIgnoreCase("Slip fri"))
                        prisonCon.runCase();
                    if (choice.equalsIgnoreCase("Handel"))
                        tradeCon.runCase();
                    if (choice.equalsIgnoreCase("Pantsætning"))
                        pawnCon.runCase();
                } else {

                }

            }while (currentPlayer.getIsActive());

            currentPlayerNum++;
        }
    }

}
