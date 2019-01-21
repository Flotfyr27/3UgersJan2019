package Domain.GameElements;
import Domain.GameElements.Entities.ChanceCardStack;
import Domain.GameElements.Entities.DiceTray;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.ChanceField.ChanceField;
import Domain.GameElements.Fields.EmptyField;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Fields.JailorField;
import Domain.GameElements.Fields.Ownable.CompanyField;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
import Domain.GameElements.Fields.Ownable.ShippingField;
import Domain.GameElements.Fields.TaxField;
import TechnicalServices.GameLogic.Values;
import UI.GUI.GuiHandler;

import java.awt.*;

public class Board {
    private Field[] fields;
    private Player[] players;
    private DiceTray diceTray;

    private static Board boardInstance;

    /**
     * the singleton pattern
     * @return the single instance
     */
    public static Board getInstance() {
        if (boardInstance == null){
            boardInstance = new Board();
            return boardInstance;
        } else {
            return boardInstance;
        }

    }

    /**
     * Constructor for Board
     */
    private Board() {
        diceTray = new DiceTray();
        setFields();
    }

    /**
     * Method to initialize all externally dependent variables when board is first created
     *
     * @param numberOfPlayers Integer to determine number of players
     */
    public void initBoard(int numberOfPlayers, boolean isPresentation) {
        players = new Player[numberOfPlayers];
        String name;
        for (int i = 0; i < numberOfPlayers; i++) {

            if (!isPresentation) {
                do {
                    name = GuiHandler.getInstance().getUserString("Indtast dit navn spiller " + (i + 1));
                    if (name.equalsIgnoreCase("") || isNameTaken(name, players))
                        GuiHandler.getInstance().giveMsg("Dit navn er enten tomt eller optaget. Indtast venligst et nyt navn");
                } while (name.equalsIgnoreCase("") || isNameTaken(name, players));
                players[i] = new Player(name);
            }else{
                players[i] =  new Player("Player" + i);
            }
        }

        ChanceCardStack.getStackInstance().initializeCards(players, fields);
    }

    /**
     * Method to get an array of all players
     *
     * @return Array filled with player objects
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Initial method to set all fields to the game board
     */
    private void setFields() {
        fields = new Field[40];
        fields[0] = (new EmptyField("START", "Hver gang de passerer START, modtag kr. 4.000", Color.RED));
        fields[1] = (new PropertyField("Rødovrevej", "kr. 1.200", Color.CYAN, 1200, 1000));
        fields[2] = (new ChanceField("?", "Prøv lykken", Color.BLACK));
        fields[3] = (new PropertyField("Hvidovrevej", "kr. 1.200", Color.CYAN, 1200, 1000));
        fields[4] = (new TaxField("Betal indkomstskat: 10% eller kr. 4.000", "Skat", Color.GREEN, 2));
        fields[5] = (new ShippingField("Helsingør-Helsingborg", "kr. 4.000", Color.BLUE, 4000));
        fields[6] = (new PropertyField("Roskildevej", "kr. 2.000", Color.ORANGE, 2000, 1000));
        fields[7] = (new ChanceField("?", "Prøv lykken", Color.BLACK));
        fields[8] = (new PropertyField("Valby Langgade", "kr. 2.000", Color.ORANGE, 2000, 1000));
        fields[9] = (new PropertyField("Allégade", "kr. 2.400", Color.ORANGE, 2400, 1000));
        fields[10] = (new EmptyField("På besøg\nI fængsel", "På besøg", Color.DARK_GRAY));
        fields[11] = (new PropertyField("Frederiksberg Allé", "kr. 2.800", Color.GREEN, 2800, 2000));
        fields[12] = (new CompanyField("Tuborg", "kr. 3.000", Color.RED, 3000, diceTray));
        fields[13] = (new PropertyField("Bülowsvej", "kr. 2.800", Color.GREEN, 2800, 2000));
        fields[14] = (new PropertyField("Gl. Kongevej", "kr. 3.200", Color.GREEN, 3200, 2000));
        fields[15] = (new ShippingField("Mols-Linien", "kr. 4.000", Color.RED, 4000));
        fields[16] = (new PropertyField("Bernstorffsvej", "kr. 3.600", Color.LIGHT_GRAY, 3600, 2000));
        fields[17] = (new ChanceField("?", "Prøv lykken", Color.BLACK));
        fields[18] = (new PropertyField("Hellerupvej", "kr. 3.600", Color.LIGHT_GRAY, 3600, 2000));
        fields[19] = (new PropertyField("Strandvejen", "kr. 4.000", Color.LIGHT_GRAY, 4000, 1000));
        fields[20] = (new EmptyField("Gratis Parkering", "Gratis P", Color.WHITE));
        fields[21] = (new PropertyField("Trianglen", "kr. 4.400", Color.RED, 4400, 3000));
        fields[22] = (new ChanceField("?", "Prøv lykken", Color.BLACK));
        fields[23] = (new PropertyField("Østerbrogade", "kr. 4.400", Color.RED, 4400, 3000));
        fields[24] = (new PropertyField("Grønningen", "kr. 4.800", Color.RED, 4800, 3000));
        fields[25] = (new ShippingField("Gedser-Rostock", "kr. 4.000", Color.BLUE, 4000));
        fields[26] = (new PropertyField("Bredgade", "kr. 5.200", Color.WHITE, 5200, 3000));
        fields[27] = (new PropertyField("Kgs. Nytorv", "kr. 5.200", Color.WHITE, 5200, 3000));
        fields[28] = (new CompanyField("Coca-Cola", "kr. 3.000", Color.RED, 3000, diceTray));
        fields[29] = (new PropertyField("Østergade", "kr. 5.600", Color.WHITE, 5600, 3000));
        fields[30] = (new JailorField("De fængsles", "De fængsles", Color.DARK_GRAY));
        fields[31] = (new PropertyField("Amagertorv", "kr. 6.000", Color.YELLOW, 6000, 4000));
        fields[32] = (new PropertyField("Vimmelskaftet", "kr. 6.000", Color.YELLOW, 6000, 4000));
        fields[33] = (new ChanceField("?", "Prøv lykken", Color.BLACK));
        fields[34] = (new PropertyField("Nygade", "kr. 6.400", Color.YELLOW, 6400, 4000));
        fields[35] = (new ShippingField("Rødby-Puttgarden", "kr. 4.000", Color.BLUE, 4000));
        fields[36] = (new ChanceField("?", "Prøv lykken", Color.BLACK));
        fields[37] = (new PropertyField("Frederiksberggade", "kr. 7.000", Color.MAGENTA, 7000, 4000));
        fields[38] = (new TaxField("Ekstraordinær statsskat: Betal", "kr. 2.000", Color.GREEN, 1));
        fields[39] = (new PropertyField("Rådhuspladsen", "kr. 8.000", Color.MAGENTA, 8000, 4000));
    }

    /**
     * @return The array of fields on the board
     */
    public Field[] getFields() {
        return fields;
    }

    /**
     * @return returns the diceTray in the board class
     */
    public DiceTray getDiceTray(){
        return diceTray;
    }

    /**
     * Makes a string with a message for the player
     * @param fieldPos the position of the field in the field array
     * @return A message in the form of a string
     */
    public String getRentString(int fieldPos){
        if (fields[fieldPos].getClass().getSuperclass().equals(OwnableField.class)){
            if (fields[fieldPos].getClass().equals(PropertyField.class)) {
                return "" + Values.rentPrice(fieldPos, ((PropertyField) fields[fieldPos]).getHouses());
            } else if (fields[fieldPos].getClass().equals(CompanyField.class)) {
                int rent =  Values.rentPrice(fieldPos, 0);
                if (((OwnableField)fields[fieldPos]).ownsAll())
                    rent *= 2;
                return "Terningeslag * " + rent;
            } else {

                return "Antal ejede Rederier * " + Values.rentPrice(fieldPos, 0);
            }
        } else {
            throw new IllegalArgumentException("Only OwnableField's allowed");
        }
    }

    /**
     * This method checks whether a name already has been claimed by another player
     * @param name The name of the current player entering their name
     * @param players The lis tof players
     * @return true if another player already took the same name
     */
    private boolean isNameTaken(String name, Player[] players){
        for (int i = 0; i < players.length; i++){
            for (int j = 0; j < i; j++) {
                if(players[j] != null) {
                    if(players[j].getName().equalsIgnoreCase(name))
                        return true;
                }
            }
        }
        return false;
    }
}
