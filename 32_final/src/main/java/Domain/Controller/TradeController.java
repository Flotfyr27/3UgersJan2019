package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
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
        Object chosenTradeObject;
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

        String tradeObject = guiHandler.makeButtons("Hvad vil du handle med?", "Benådninger", "Ejendomme");

        if (tradeObject.equalsIgnoreCase("Benådninger")){
            chosenTradeObject = owner.getJailCards();
        } else {
            fieldNames = getTradeFields(owner);
            chosenTradeObject = getChosenField(owner, fieldNames);
        }

        if (chosenTradeObject == null ||
                (chosenTradeObject.getClass() == Integer.class && ((Integer) chosenTradeObject) <= 0)) {
            StringBuilder msg = new StringBuilder();
                   msg.append(owner.getName());
                    msg.append(" har ikke nogen ");
            if (tradeObject.equalsIgnoreCase("Ejendomme"))
                msg.append("grunde");
            else
                msg.append("benådninger");
            msg.append(" at handle med.");
            guiHandler.giveMsg(msg.toString());
            return;
        }

        try {
            price = getUserPrice(owner, receiver, chosenTradeObject);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        makeTransaction(price, chosenTradeObject, owner, receiver);
        guiHandler.updateBalance(board.getPlayers());
    }

    /**
     * Moves the money and the ownership of a tile from one party to the other.
     *
     * @param price The amount of money paid for the OwnableField
     * @param chosenTradeObject The field being traded
     * @param owner The one owning the field before the trade
     * @param receiver The one getting the field
     */
    private void makeTransaction(int price, Object chosenTradeObject, Player owner, Player receiver) {
        //Take money from the buying player and add to selling player
        if (receiver.getAccount().getScore() >= price) {
            receiver.getAccount().changeScore(-price);
            owner.getAccount().changeScore(price);
            if (!chosenTradeObject.getClass().equals(Integer.class)) {
                owner.getOwnedFields().remove((OwnableField) chosenTradeObject);
                receiver.getOwnedFields().add((OwnableField) chosenTradeObject);
                ((OwnableField)chosenTradeObject).setOwner(receiver);
            } else {
                owner.removeJailCards();
                receiver.addJailCards();
            }
        } else {
            guiHandler.giveMsg(receiver.getName() + " har ikke råd til denne handel.");
            return;
        }
        guiHandler.giveMsg(receiver.getName() + " har købt " + chosenTradeObject.toString() +
                " for kr. " + price + " af " + owner.getName());
    }

    /**
     * Asks the player for an integer price until one is selected
     *
     * @param owner The owner of the field
     * @param receiver The one standing to receive the field
     * @param chosenTradeObject The field being traded
     * @return The agreed upon price
     */
    private int getUserPrice(Player owner, Player receiver, Object chosenTradeObject) {
        int salesPrice;
        String confirmationOfSale;
        do {
            salesPrice = guiHandler.getUserInt("Indtast salgsprisen: ");

            confirmationOfSale = guiHandler.makeButtons(owner.getName() + " sælger " +
                    chosenTradeObject.toString() + " til " + receiver.getName() + " for kr. " + salesPrice +
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
            String fieldString = guiHandler.makeButtons("Vælg felt du vil handle med", fieldNames);
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
        String targetPlayer = guiHandler.makeButtons("Vælg spiller du vil handle med", names);
        for (int n = 0; n < board.getPlayers().length; n++) {
            if (targetPlayer.equals(board.getPlayers()[n].getName())) {
                return board.getPlayers()[n];
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
                names[i++] = board.getPlayers()[n].getName();
        }
        return names;
    }
}
