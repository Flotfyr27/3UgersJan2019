package Domain.GameElements.Fields;

import java.awt.*;

public abstract class Field {
    /**
     * These variables are the name, subtext and colour of a field.
     */
    private String name, subtext;
    private Color bgColour;

    /**
     * Constructor for the base parent field. This has a name, subtext and color.
     * @param name This string represents the name of the field
     * @param subtext This string represents the subtext of a field.
     * @param bgColour This colour represents the background color of a field.
     */
    public Field(String name, String subtext, Color bgColour){
        this.name = name;
        this.subtext = subtext;
        this.bgColour = bgColour;
    }

    /**
     * This method returns the name of a field
     * @return String representative of the fields name.
     */
    public String getName(){
        return name;
    }

    /**
     * This method returns the subtext of a field
     * @return String which represents the fields subtext
     */
    public String getSubtext(){
        return subtext;
    }

    /**
     * Method returns the background colour of the field.
     * @return Color-object representative of the background colour.
     */
    public Color getBgColor(){
        return bgColour;
    }

    /**
     * Abstract method to determine what happens when a player lands on a field.
     * @param current The current player
     * @param players An array of all players
     * @param fields An array of all fields
     */
   // public abstract void landOnAction(Player current, Player[] players, Field[] fields){};

    /**
     * Does... something
     * @param current The current player
     * @return A string depending on the field.
     */
    //public abstract String getMessage(Player current);
}
