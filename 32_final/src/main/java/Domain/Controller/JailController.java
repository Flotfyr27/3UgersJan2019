package Domain.Controller;

import Domain.GameElements.Entities.DiceTray;
import Domain.GameElements.Entities.Player;
import TechnicalServices.GameLogic.GameLogic;
import UI.GUI.GuiHandler;

public class JailController {
    private GuiHandler guiHandler = GuiHandler.getInstance();
    private DiceTray diceTray;

    private static JailController instance;

    /**
     * Making the JailController a singleton
     *
     * @return The only instance of the class
     */
    public static JailController getInstance() {
        if (instance == null) {
            instance = new JailController();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * Constructor
     */
    private JailController() {
        diceTray = new DiceTray();
    }

    /**
     * RunCase in which the player chooses between two options for getting out of jail.
     *
     * @param player
     */
    public void runCase(Player player) {
        player.setJailTime(player.getJailTime() + 1); //adds 1 to timeInJail. After 3 rounds the player is forced to pay bail to get out.
        String buttons = guiHandler.makeButtons("Du er i fængsel. hvad vil du foretage dig?", "Betal kr.1.000, og ryk ud af fængsel", "Slå terninger");

        //Choice of making bail and throwing the dice
        if (buttons.equals("Betal kr.1.000, og ryk ud af fængsel")) {
            try {
                player.getAccount().changeScore(-1000);
            } catch (RuntimeException e) {
                GameLogic.cantPay(player, -1000);
            }
            MoveController.getInstance().runCase(player); //The dice are thrown in the moveController
            player.setJailTime(-1);
        }
        //Throwing dice to get double values. If both dice are the same, the player gets out of jail.
        else if (buttons.equals("Slå terninger")) {
            diceTray.Roll();
            guiHandler.showDice(diceTray.getValue1(), diceTray.getValue2());

            //if/else statement which determines what to do when throwing double dice or not.
            if (diceTray.IsDoubleValue()) {
                guiHandler.showDice(diceTray.getValue1(), diceTray.getValue2());
                guiHandler.giveMsg("Du slog dobbelt, tillyke! Du kommer ud af fængslet.");
                MoveController.getInstance().runCase(player, diceTray.getSum(), diceTray.IsDoubleValue()); //The dice are thrown in the moveController
                player.setJailTime(-1);
            } else {
                //The player is forced to make bail
                if (player.getJailTime() == 3) {
                    guiHandler.giveMsg("Du slog desværre ikke dobbelt. Du har ikke flere forsøg, du er nødt til at betale kr. 1000 og slipper så fri.");
                    try {
                        player.getAccount().changeScore(-1000);
                    } catch (RuntimeException e) {
                        GameLogic.cantPay(player, -1000);
                    }
                    MoveController.getInstance().runCase(player); //The dice are thrown in the moveController
                    player.setJailTime(-1);
                } else {
                    guiHandler.showDice(diceTray.getValue1(), diceTray.getValue2());
                    guiHandler.giveMsg("Du slog desværre ikke dobbelt. Bedre held næste gang");
                }
            }
        }
    }
}