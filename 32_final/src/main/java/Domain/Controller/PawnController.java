package Domain.Controller;


import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
import Domain.GameElements.Board;
import TechnicalServices.GameLogic.GameLogic;
import UI.GUI.GuiHandler;

public class PawnController {

    private GuiHandler guiHandler;
    private Board board;
    private static PawnController instance;

    /**
     * constructor
     *
     */
    public PawnController() {
        guiHandler = GuiHandler.getInstance();
        board = Board.getInstance();
    }

    /**
     *
     */
    public static PawnController getInstance(){
        if (instance == null) {
            instance = new PawnController();
            return instance;
        }
        else return instance;
    }

    /**
     *
     * @param player
     */
    public void runCase(Player player){
        OwnableField chosenField;
        String[] fieldNames;

        String pawnChoice = guiHandler.makeButtons("Vil du pante en grund eller købe en grund tilbage?","Pante", "Købe tilbage");
        if(pawnChoice.equalsIgnoreCase("Pante")) {
            fieldNames = getUnpawnedTradeFields(player);
            if(fieldNames.length > 0 && !(fieldNames[0]==null)) {
                chosenField = getChosenUnpawnedField(player, fieldNames);
                pawnProperty(chosenField, player);
            }
            else{
                guiHandler.makeButtons("Du har ingen grunde at pante", "Ok");
                return;
            }
        }
        else {
            fieldNames = getPawnedTradeFields(player);
            if(fieldNames.length > 0 && !(fieldNames[0] == null)) {
                chosenField = getChosenPawnedField(player, fieldNames);
                unPawn(chosenField, player);
            } else { guiHandler.makeButtons("Du har ingen grunde, du kan købe tilbage", "Ok");
                return;}
        }

        guiHandler.updateBalance(board.getPlayers());
    }
    /**
     * Creates a button for each ownableField which hasn't been pawned and returns the field chosen by the user
     *
     * @param owner      The player who's fields can be chosen
     * @param fieldNames An array of all the possible fields to trade
     * @return the chosen field
     */
    private OwnableField getChosenUnpawnedField(Player owner, String[] fieldNames) {
        //Select a field to trade based on user input
        if (fieldNames.length > 0) {
            String fieldString = guiHandler.makeButtons("Vælg et felt at pante", fieldNames);
            for (int n = 0; n < owner.getOwnedFields().size(); n++) {
                if (fieldString.equals(fieldNames[n])) {
                    return owner.getOwnedFields().get(n);
                }
            }
        } else {
            return null;
        }
        throw new RuntimeException("getChosenField() returned no value");
    }
    /**
     * Creates a button for each ownableField which has been pawned and returns the field chosen by the user
     *
     * @param owner      The player who's fields can be chosen
     * @param fieldNames An array of all the possible fields to trade
     * @return the chosen field
     */
    private OwnableField getChosenPawnedField(Player owner, String[] fieldNames) {
        //Select a field to trade based on user input
        if (fieldNames.length > 0) {
            String fieldString = guiHandler.makeButtons("Vælg et felt at købe tilbage", fieldNames);
            for (int n = 0; n < owner.getOwnedFields().size(); n++) {
                if (fieldString.equals(fieldNames[n])) {
                    return owner.getOwnedFields().get(n);
                }
            }
        } else {
            return null;
        }
        throw new RuntimeException("getChosenField() returned no value");
    }

    /**
     * Creates a list of the fields owned by a player
     * @param owner the player
     * @return list of fields
     */
    private String[] getUnpawnedTradeFields(Player owner) {
        String[] fieldNames = new String[owner.getOwnedFields().size()];
        for (int n = 0; n < owner.getOwnedFields().size(); n++) {
            if(!owner.getOwnedFields().get(n).getIsPawned())
                fieldNames[n] = owner.getOwnedFields().get(n).getName();
        }
        return fieldNames;
    }
    /**
     * Creates a list of the fields owned by a player
     * @param owner the player
     * @return list of fields
     */
    private String[] getPawnedTradeFields(Player owner) {
        String[] fieldNames = new String[owner.getOwnedFields().size()];
        for (int n = 0; n < owner.getOwnedFields().size(); n++) {
            if(owner.getOwnedFields().get(n).getIsPawned())
                fieldNames[n] = owner.getOwnedFields().get(n).getName();
        }
        return fieldNames;
    }
    /**
     * Boolean checking if the field has any hotels or houses
     *
     * @return
     */
    private boolean hasBuildings(OwnableField ownableField) {
        if (ownableField.getHouses() == 0 && ownableField.getHotel()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method checks if the field is a property field
     *
     * @return
     */
    private boolean isPropertyField(OwnableField ownableField) {
        if (ownableField.getClass() == PropertyField.class) {
            return true;
        } else {
            return false;
        }
    }

    private int pawnValue(OwnableField ownableField) {
        return ownableField.getPrice() / 2;

    }

    /**
     * Method that pawn our properties.
     */
    private void pawnProperty(OwnableField ownableField, Player p) {
        int buildingsWorth;
        buildingsWorth = ownableField.getWorth() - ownableField.getPrice();

        if (hasBuildings(ownableField) && isPropertyField(ownableField)) {
            int numberOfHouses = ownableField.getHouses();
            ownableField.removeHouse(numberOfHouses);
            p.getAccount().changeScore(buildingsWorth);
        } else {
            p.getAccount().changeScore(pawnValue(ownableField));
        }
            ownableField.setIsPawned(true);
    }

    /**
     * Multiplication of
     * Cast the double as an int.
     */
    private int buyPawnBackValue(OwnableField ownableField, Player p) {

        return (int) (-pawnValue(ownableField) * 1.1 - ((int) (pawnValue(ownableField) * 1.1) % 50));

    }

    /**
     * Getting boolean from ownableField and allows you to unpawn it.
     */
    private void unPawn(OwnableField ownableField, Player p) {
        if (p.getAccount().getScore() - buyPawnBackValue(ownableField, p) > 0) {
               p.getAccount().changeScore(buyPawnBackValue(ownableField, p));
               ownableField.setIsPawned(false);
        }
        else
            guiHandler.giveMsg("Du har ikke råd til at købe denne grund tilbage");
    }

}

