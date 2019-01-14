package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Fields.Ownable.OwnableField;
import UI.GUI.GuiHandler;

public class TradeController {
    private GuiHandler guiHandler;
    private Board board;

    public TradeController() {
        guiHandler = GuiHandler.getInstance();
        board = Board.getInstance();
    }

    public void runCase(Player playerTrading) {
        OwnableField chosenField;
        Player chosenPlayer;
        String[] names;
        String[] fieldNames;
        Player owner, receiver;
        int price;

        String tradeType = guiHandler.makeButtons("Vil du sælge en ejendom eller købe en andens?", "Sælg", "Køb");

        names = getPlayerNames(playerTrading);
        chosenPlayer = getChosenPlayer(names);

        if (tradeType.equalsIgnoreCase("Sælg")) {
            owner = playerTrading;
            receiver = chosenPlayer;
        } else {
            owner = chosenPlayer;
            receiver = playerTrading;
        }

        fieldNames = getTradeFields(owner);
        chosenField = getChosenField(owner, fieldNames);

        if (chosenField == null) {
            guiHandler.makeButtons("Du har ingen grunde at handle med", "Ok");
            return;
        }

        try {
            price = getUserPrice(owner, receiver, chosenField);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        makeTransaction(price, chosenField, owner, receiver);
        guiHandler.updateBalance(board.getPlayers());
    }

    /**
     * Moves the money and the ownership of a tile from one party to the other.
     *
     * @param price The amount of money paid for the OwnableField
     * @param chosenField The field being traded
     * @param owner The one owning the field before the trade
     * @param receiver The one getting the field
     */
    private void makeTransaction(int price, OwnableField chosenField, Player owner, Player receiver) {
        //Take money from the buying player and add to selling player
        if (receiver.getAccount().getScore() >= price) {
            receiver.getAccount().changeScore(-price);
            owner.getAccount().changeScore(price);
            owner.getOwnedFields().remove(chosenField);
            receiver.getOwnedFields().add(chosenField);
            chosenField.setOwner(receiver);
        } else {
            guiHandler.giveMsg(receiver.getName() + " har ikke råd til denne handel.");
            return;
        }
        guiHandler.giveMsg(receiver.getName() + " har købt " + chosenField.getName() +
                " for kr. " + price + " af " + owner.getName());
    }

    /**
     * Asks the player for an integer price until one is selected
     *
     * @param owner The owner of the field
     * @param receiver The one standing to receive the field
     * @param chosenField The field being traded
     * @return The agreed upon price
     */
    private int getUserPrice(Player owner, Player receiver, Field chosenField) {
        int salesPrice;
        String confirmationOfSale;
        do {
            salesPrice = guiHandler.getUserInt("Indtast salgsprisen: ");

            confirmationOfSale = guiHandler.makeButtons(owner.getName() + " sælger " +
                    chosenField.getName() + " til " + receiver.getName() + " for kr. " + salesPrice +
                    "." + "Er dette korrekt?", "Ja", "Nej", "Afbryd køb");
            if (confirmationOfSale.equals("Afbryd køb")) {
                guiHandler.giveMsg("Køb afbrudt!");
                throw new RuntimeException("Køb afbrudt");
            }
        } while (confirmationOfSale.equals("Nej"));
        return salesPrice;
    }

    /**
     * Creates a button for each ownableField and returns the field chosen by the user
     *
     * @param owner      The player who's fields can be chosen
     * @param fieldNames An array of all the possible fields to trade
     * @return the chosen field
     */
    private OwnableField getChosenField(Player owner, String[] fieldNames) {
        //Select a field to trade based on user input
        if (fieldNames.length > 0) {
            String fieldString = guiHandler.makeButtons("Select field to trade", fieldNames);
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
     * returns the names of all OwnableFields owned by owner
     *
     * @param owner the player whose fields are returned as a string
     * @return string of all owned Fields in owner
     */
    private String[] getTradeFields(Player owner) {
        String[] fieldNames = new String[owner.getOwnedFields().size()];
        for (int n = 0; n < owner.getOwnedFields().size(); n++) {
            fieldNames[n] = owner.getOwnedFields().get(n).getName();
        }
        return fieldNames;
    }

    /**
     * Makes a button for each possible trading partner and returns the selected player
     *
     * @param names the names of all possible trading partners
     * @return selected player
     */
    private Player getChosenPlayer(String[] names) {
        String targetPlayer = guiHandler.makeButtons("Select a player to trade with", names);
        for (int n = 0; n < board.getPlayers().length; n++) {
            if (targetPlayer.equals(board.getPlayerAtIndex(n).getName())) {
                return board.getPlayerAtIndex(n);
            }
        }

        throw new NullPointerException("Player not found");
    }


    /**
     * saves all the playernames into an array
     *
     * @param playerTrading the trading player (the player being excluded
     * @return String[] of all the possible choices of trading partners
     */
    private String[] getPlayerNames(Player playerTrading) {
        int i = 0;
        String[] names = new String[board.getPlayers().length - 1];

        for (int n = 0; n < board.getPlayers().length; n++) {
            if (!playerTrading.equals(board.getPlayers()[n]))
                names[i++] = board.getPlayerAtIndex(n).getName();
        }
        return names;
    }
}
