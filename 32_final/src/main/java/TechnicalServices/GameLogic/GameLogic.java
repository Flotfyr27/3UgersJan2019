package main.java.TechnicalServices.GameLogic;

import main.java.Domain.GameElements.Entities.Player;

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
    public void movingPastStart(Player player, int destination){
       if(player.getPos()> destination)
        player.getAccount().changeScore(4000);
    }

    public boolean hasLost(Player player){
        if(player.getAccount().getScore() <= 0)
            return true;
        else return false;
    }

    public boolean lastManStanding(Player[] players){
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
