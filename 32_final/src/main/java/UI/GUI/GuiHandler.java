/**
 * All GuiHandler related classes are kept here.
 */
package UI.GUI;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.*;
import Domain.GameElements.Fields.Ownable.*;
import Domain.GameElements.Fields.ChanceField.*;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GuiHandler {
    private GUI gui;
    private GUI_Field[] gui_fields = new GUI_Field[24];
    private GUI_Player[] guiPlayers;

    private static GuiHandler guiHandlerInstance;

    /**
     * returns the instance of the GuiHandler
     * this method ensures that all classes can access the same GuiHandler instance (singleton)
     * @return
     */
    public static GuiHandler getInstance() {
        if (guiHandlerInstance != null)
            return guiHandlerInstance;
        else
            throw new NullPointerException("an instance of the GuiHandler has not been instantiated yet");
    }

    /**
     * Creates an instance of the GuiHandler class if none exists otherwise throws exception.
     * @param fields
     * @return
     */
    public static GuiHandler instantiateGui(Field[] fields){
        if (guiHandlerInstance == null) {
            guiHandlerInstance = new GuiHandler(fields);
            return getInstance();
        } else {
            throw new IllegalStateException("An instance has already been created, use getInstance() instead.");
        }
    }

    /**
     * Constructor. it is private to make sure it cannot be used externally.
     * @param fields
     */
    private GuiHandler(Field[] fields){//Field[] fields
        for(int i = 0; i < gui_fields.length; i++){
            if(fields[i].getClass().equals(EmptyField.class) && i == 0){
                gui_fields[i] = (new GUI_Start(fields[i].getName(), fields[i].getSubtext(), "", fields[i].getBgColour(), null));
            }else if(fields[i].getClass().equals(PropertyField.class)){
                PropertyField propertyField = (PropertyField) fields[i];
                gui_fields[i] = (new GUI_Street(fields[i].getName(), fields[i].getSubtext(), "", Integer.toString(propertyField.getPrice()), fields[i].getBgColour(), null));
            }else if(fields[i].getClass().equals(EmptyField.class)){
                gui_fields[i] = (new GUI_Street(fields[i].getName(), fields[i].getSubtext(), "", "0", fields[i].getBgColour(), null));//This one be causing trouble
            }else if(fields[i].getClass().equals(ChanceField.class)){
                gui_fields[i] = (new GUI_Chance(fields[i].getName(), fields[i].getSubtext(), "", fields[i].getBgColour(), null));
            }
        }

        gui = new GUI(gui_fields, Color.lightGray);
    }

    /**
     * Methods returns an integer given by a player
     * @param min The minimum number of players
     * @param max The maximum number of players
     */
    public int getNumberOfPlayers(int min, int max){
        int output;

        do { //Made this loop due to us sometimes being able to choose any number of players despite min/max value
            output = gui.getUserInteger("Choose between 2 and 4 players", min, max);
        } while (output < min || output > max);
        return output;
    }

    public void initGui(Player[] p){
        GUI_Car.Type carType;
        Color primaryColor;
        //Create players
        guiPlayers = new GUI_Player[p.length];
        for(int i = 0; i < p.length; i++){
            switch (i){
                case 0:{
                    carType = GUI_Car.Type.CAR;
                    primaryColor = Color.RED;
                    break;
                }
                case 1:{
                    carType = GUI_Car.Type.RACECAR;
                    primaryColor = Color.GREEN;
                    break;
                }
                case 2:{
                    carType = GUI_Car.Type.TRACTOR;
                    primaryColor = Color.CYAN;
                    break;
                }
                case 3:{
                    carType = GUI_Car.Type.UFO;
                    primaryColor = Color.MAGENTA;
                    break;
                }
                default:{
                    carType = GUI_Car.Type.CAR;
                    primaryColor = Color.BLUE;
                    break;
                }
            }
            guiPlayers[i] = new GUI_Player("Player" + (i+1), p[i].getAccount().getScore(), new GUI_Car(primaryColor, Color.WHITE, carType, GUI_Car.Pattern.HORIZONTAL_LINE));
            //Add players to GUI
            gui.addPlayer(guiPlayers[i]);
            guiPlayers[i].setBalance(p[i].getAccount().getScore());
            //Adds the players car to the board at START
            gui_fields[0].setCar(guiPlayers[i], true);
        }


    }

    public void updateGui(Player[] pArr, Field[] f){

        boolean carMoved = false;
        //moves players step by step
        for (int i = 0; i < gui_fields.length; i++) {
            for (int j = 0; j < guiPlayers.length; j++) {
                if (gui_fields[i].hasCar(guiPlayers[j]) && pArr[j].getPos() != i){
                    gui_fields[(i+1)% gui_fields.length].setCar(guiPlayers[j], true);
                    carMoved = true;
                }
            }

            gui_fields[i].removeAllCars();
            for (int j = 0; j < guiPlayers.length; j++) {
                if (pArr[j].getPos() == i){
                    gui_fields[i].setCar(guiPlayers[j], true);
                }
            }


            try {
                if (carMoved){
                    carMoved = false;
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Update player balance
        for(int i = 0; i < pArr.length; i++){
            guiPlayers[i].setBalance(pArr[i].getAccount().getScore());
        }

        //Update ownership of tile
        Player owner;
        for(int i = 0; i < gui_fields.length; i++){
            if (f[i].getClass().equals(PropertyField.class)) {
                owner = ((PropertyField) f[i]).getOwner();
                if (owner != null) {
                    gui_fields[i].setDescription("Owner: " + owner.getName());
                } else {
                    gui_fields[i].setDescription("No owner");
                }
            }
        }


        // maybe use gui.displayChanceCard(message);
        //TODO : Display chanceCard text

    }

    public void showDie(int value){
        gui.setDie(value);
    }

    public void msgInMidle(String msg){
        gui.displayChanceCard(msg);
    }

    public void waitForRoll(String msg){
        gui.getUserButtonPressed(msg, "Roll");
    }

    public void giveMsg(String msg){
        gui.showMessage(msg);
    }

    //TODO lav en toString metode der udskriver alle vores felter.


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (GUI_Field f : gui_fields){
            builder.append(f + "\n");
        }

        return builder.toString();
    }


}



