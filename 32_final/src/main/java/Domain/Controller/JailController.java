package Domain.Controller;

import Domain.GameElements.Entities.DiceTray;
import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;

public class JailController {
    private GuiHandler guiHandler = GuiHandler.getInstance();
    private int timeInJail;
    private DiceTray diceTray;

    private static JailController instance;

    /**
     * Making the JailController a singleton
     * @return
     */
    public static JailController getInstance(){
        if(instance==null){
            instance = new JailController();
            return instance;
        }else{
            return instance;
        }
    }

    /**
     * Constructor
     */
    private JailController(){
        diceTray = new DiceTray();
    }

    /**
     * RunCase in which the player chooses between two options for getting out of jail.
     * @param player
     */
    public void runCase(Player player) {
        timeInJail = 0;

        do {
            player.setJailTime(++timeInJail); //adds 1 to timeInJail. After 3 rounds the player is forced to pay bail to get out.
            String buttons = guiHandler.makeButtons("Du er i fængsel. hvad vil du foretage dig?", "Betal kr.1.000, og ryk ud af fængsel", "Slå terninger");

            //Choice of making bail and throwing the dice
            if(buttons.equals("Betal kr.1.000, og ryk ud af fængsel")) {
                player.getAccount().changeScore(-1000);
                MoveController.getInstance().runCase(player); //The dice are thrown in the moveController
                player.setJailTime(-1);
                return;
            }
                //Throwing dice to get double values. If both dice are the same, the player gets out of jail.
                else if(buttons.equals("Slå terninger")){
                    diceTray.Roll();
                    guiHandler.showDice(diceTray.getValue1(), diceTray.getValue2());

                    //if/else statement which determines what to do when throwing double dice or not.
                    if(diceTray.IsDoubleValue()) {
                        MoveController.getInstance().runcase(player, diceTray.getSum(), diceTray.IsDoubleValue()); //The dice are thrown in the moveController
                        player.setJailTime(-1);
                        return;
                    }else if(!diceTray.IsDoubleValue()){

                        guiHandler.msgInMiddle("Du slog desværre ikke dobbelt. Bedre held næste gang");
                        return;

                    }
                }

            }while(player.getJailTime()<3);

        //The player is forced to make bail
        if(player.getJailTime()==3){
            player.getAccount().changeScore(-1000);
            MoveController.getInstance().runCase(player); //The dice are thrown in the moveController
            player.setJailTime(-1);
        }

    }

}
