package Domain.GameElements;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.ChanceField.ChanceField;
import Domain.GameElements.Fields.EmptyField;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Fields.Ownable.PropertyField;
import Domain.GameElements.Fields.TaxField;

import java.awt.*;

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
    public Player[] getPlayers(){
        return players;
    }

    private void setFields(){
    fields = new Field[40];
    fields[0] = (new EmptyField("START", "Hver gang de passerer START, modtag kr. 4000", Color.RED));
    fields[1] = (new PropertyField("Rødovrevej", "kr. 1.200", Color.CYAN, 1200, 1000));
    fields[2] = (new ChanceField("Prøv lykken", "?", Color.BLACK));
    fields[3] = (new PropertyField("Hvidovrevej", "kr. 1.200", Color.CYAN, 1200, 1000));
    fields[4] = (new TaxField("Skat", "Betal indkomstskat: 10% eller kr. 4.000", Color.GREEN, ))
    }
}
