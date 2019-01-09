package Domain.GameElements;

import Domain.GameElements.Fields.Field;

public class Board {
private Field[] fields;


    private void setFields(){
    fields = new Field[40];
    fields[0] = (new (EmptyField("START", "Hver gang de passerer START, modtag kr. 4000")));
    }
}
