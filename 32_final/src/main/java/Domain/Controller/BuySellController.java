package Domain.Controller;

import Domain.GameElements.Board;
import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Ownable.OwnableField;
import Domain.GameElements.Fields.Ownable.PropertyField;
import UI.GUI.GuiHandler;


import java.awt.*;

public class BuySellController {
    private GuiHandler guiHandler = GuiHandler.getInstance();
    private boolean notDone;
    private Board board;

    private static BuySellController instance;

    /**
     * Making the BuySellController a singleton
     * @return
     */
    public static BuySellController getInstance(){
        if(instance == null){
            instance = new BuySellController();
            return instance;
        }else{
            return instance;
        }
    }

    private BuySellController(){
        board = Board.getInstance();
    }




    public void runCase(Player player){

    Color[] coloursOnBoard = {Color.CYAN, Color.ORANGE, Color.GREEN, Color.LIGHT_GRAY, Color.RED, Color.WHITE, Color.YELLOW, Color.MAGENTA};
    String propertyButtons;
    String[] propertyNames;
        PropertyField propertyField;
        Boolean buySell;
        buySell = true;

        do{

            String buttons = guiHandler.makeButtons("Vil du købe eller sælge huse/hoteller?", "Køb", "Sælg");


            if(buttons.equalsIgnoreCase("Køb")){
                for(Color color: coloursOnBoard){
                    boolean sameOwner;
                    for (OwnableField field : OwnableField.getFieldsOfColor(color, PropertyField.class)){
                        if (field.getOwner() == player)
                            sameOwner = true;
                        else {
                            sameOwner = false;
                            break;
                        }
                    }
                    propertyNames = OwnableField.getFieldsOfColor(color, PropertyField.class).length;

                    for(int i = 0; i<OwnableField.getFieldsOfColor(color, PropertyField.class).length; i++) {
                        propertyNames[i] = OwnableField.getFieldsOfColor(color, PropertyField.class)[i].getName();
                    }
                        propertyButtons = guiHandler.makeButtons(color.toString(), propertyNames);

        }
        for(int i = 0; i<board.getFields().length; i++ ) {
            if (board.getFields()[i].getName().equals(propertyButtons)){
                propertyField = (PropertyField) board.getFields()[i];
                String buyNoBuy = guiHandler.makeButtons("Et hus på denne ejendom koster" + propertyField.getHousePrice(player) + "Vil du stadig købe?", "Ja", "Nej" );
                if (buyNoBuy.equals("Ja")){

                    if(player.getAccount().canBuy(-propertyField.getHousePrice(player))){
                        player.getAccount().changeScore(-propertyField.getHousePrice(player));
                        propertyField.addHouse();
                        guiHandler.msgInMiddle("Du har nu købt et hus til din ejendom");
                    }else{
                        guiHandler.msgInMiddle("Du har desværre ikke råd til at købe denne ejendom");
                    }
                }
                else if (buyNoBuy.equals("Nej")){
                }
                }
            }
            String buySellMore =guiHandler.makeButtons("Vil du købe eller sælge mere?", "Ja", "Nej");
            if(buySellMore.equals("nej"));
            buySell = false;
        }
    }while (buySell = true);