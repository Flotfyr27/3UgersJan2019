package Domain.Controller;

import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;

public class BuySellController {
    private GuiHandler guiHandler = GuiHandler.getInstance();
    private boolean notDone;

    private static BuySellController instance;

    /**
     * Making the BuySellController a singleton
     * @return
     */
    public static BuySellController getInstance(){
        if(instance == null){
            instance = new BuySellController();
            return instance;
        }else{
            return instance;
        }
    }

    private BuySellController(){

    }


    public void runCase(Player player){



        do{
            String buttons = guiHandler.makeButtons("Vil du købe eller sælge huse/hoteller?", "Køb", "Sælg");

            if(buttons.equalsIgnoreCase("Køb")){

            }

        }while();
    }
}
