package Domain.Controller;

import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;


public class JailController {
    private GuiHandler guiHandler = GuiHandler.getInstance();
    private int timeInJail;
    DiceTray

    Player player = new Player("playerJailed");

    private JailController(){

    }

    public void jailOptions() {
        timeInJail = 0;

        do {
            player.setJailTime(++timeInJail);
            String buttons = guiHandler.makeButtons("Du er i fængsel. hvad vil du foretage dig?", "Betal kr.1.000, og slå terninger", "Slå terninger");

            if(buttons.equals("Betal kr.1.000, og slå terninger")){
                player.getAccount().changeScore(-1000);


            }
        }
        while(player.getJailTime()<3);
    }

}
