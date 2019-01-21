package TechnicalServices.GameLogic;

import Domain.Controller.PawnController;
import Domain.Controller.TradeController;
import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;
import Domain.GameElements.Fields.Ownable.OwnableField;


public class GameLogic {


    /**
     * This methods checks when a player moves, whether they pass start
     * @param player The player who moves
     * @param destination The destination of the move
     */
    public static void movingPastStart(Player player, int destination){
       if(player.getPos()> destination%40)
        player.getAccount().changeScore(4000);
    }

    /**
     *
     * @param player It checks if the player has lost or not.
     * @return true if the player stands to loose the game
     */
    public static boolean hasLost(Player player){
        return player.getAccount().getScore() <= 0;
    }

    /**
     * Checks how many that have lost. If all except one person has lost, it returns true.
     * @param players The array of players in the game
     * @return True if only one player is left in the game
     */
    public static boolean lastManStanding(Player[] players){
        int lost = 0;
        for (Player player : players) {
            if (player.hasLost())
                lost++;
        }
        return lost == (players.length - 1);
    }

    /**
     * Method which is to be called whenever a players account score is about to go below zero. This method gives that player a chance to pawn off or sell their properties, to keep in the game, or they can opt out, if staying in the game seems a fleeting chance or right out impossible
     * @param player THe player who's score is about to go below zero
     * @param amount The amount of money(score) the player ows
     */
    public static void cantPay(Player player, int amount){
        do {
            String choice = GuiHandler.getInstance().makeButtons("Vil du pante eller give op?",
                                                            "Pante", "Handle", "Give op");
            if (choice.equalsIgnoreCase("Pante")) {
                if (player.getOwnedFields().size() > 0)
                    PawnController.getInstance().runCase(player);
                 else
                    GuiHandler.getInstance().giveMsg("Du har ikke noget at pante");

            } else if (choice.equalsIgnoreCase("Handle")) {
                if (player.getOwnedFields().size() > 0 || player.getJailCards() > 0)
                        TradeController.getInstance().runCase(player);
                 else
                    GuiHandler.getInstance().giveMsg("Du har ikke noget at sælge");

            } else {
                player.setLost(true);
                player.setIsActive(false);
                for (OwnableField f : player.getOwnedFields()) {
                    f.setOwner(null);
                }
                player.getOwnedFields().clear();
                
                GuiHandler.getInstance().removePlayerCar(player, Board.getInstance().getPlayers());
                return;
            }
        } while (player.getAccount().getScore() + amount < 0);
        player.getAccount().changeScore(amount);
    }

}
