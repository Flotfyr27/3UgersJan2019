package Domain.GameElements;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Field;

public class Board {
private Field[] fields;
private Player[] players;

public Board(int numberOfPlayers){
    initPlayers(numberOfPlayers);
}

    private void initPlayers(int numberOfPlayers){
        for(int i = 0; i < numberOfPlayers; i++){
            players[i] = new Player("Player" + (i+1));
            players[i].getAccount().changeScore(30000);
        }
    }
    private void setFields(){
    fields = new Field[40];
   //fields[0] = (new (EmptyField("START", "Hver gang de passerer START, modtag kr. 4000")));
    }
}
