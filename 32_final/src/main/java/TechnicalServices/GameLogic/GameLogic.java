package TechnicalServices.GameLogic;

import Domain.GameElements.Entities.Player;

/*
* Make list of prices through out the game in separate methods
* Win condition
*
* */
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
     * @return
     */
    public static boolean hasLost(Player player){
        if(player.getAccount().getScore() <= 0)
            return true;
        else return false;
    }

    /**
     *Checks how many that have lost. If all except one person has lost, it returns true.
     * @param
     * @return
     */
    public static boolean lastManStanding(Player[] players){
        int lost = 0;
        for (int i = 0;i <players.length;i++) {
            if (players[i].getLost())
                lost++;
        }
            if(lost == players.length-1)
                return true;
            else return false;
    }


}
