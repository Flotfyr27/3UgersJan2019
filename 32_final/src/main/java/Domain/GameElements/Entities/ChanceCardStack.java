package Domain.GameElements.Entities;

import java.util.Random;

import Domain.GameElements.Entities.Chancecard.*;

public class ChanceCardStack {
    private ChanceCard[] chanceCards;
    private int cardNum;

    private static ChanceCardStack stackInstance = null;

    /**
     * The method for getting an instance of ChanceCardStack following the singleton design pattern.
     * @return
     */
    public static ChanceCardStack getStackInstance() {
        if (stackInstance == null) {
            stackInstance = new ChanceCardStack();
        }

        return stackInstance;
    }

    /**
     * Constructor.
     * initializes, defines and shuffles the ChanceCards.
     */
    private ChanceCardStack(Player[] players){
        cardNum = 0;

        chanceCards = new ChanceCard[] {
               //
                // TODO make the chance cards
                 //
        };

        shuffleCards(chanceCards);
    }

    /**
     * returns true if there is an element after the current element in the stack.
     * @return boolean
     */
    public boolean hasNext() {
        if (cardNum+1 < chanceCards.length)
            return true;
        else
            return false;
    }

    /**
     * returns the currently selected ChanceCard.
     * @return
     */
    public ChanceCard getCurrent(){
        return chanceCards[cardNum];
    }

    /**
     * Returns the next ChanceCard in the stack.
     * @return ChanceCard
     */
    public ChanceCard next() {
        if (!hasNext()) {
            cardNum = 0; //TODO this will always exclude the first card, fix it.
        }

        return chanceCards[cardNum++];
    }

    /**
     * this method is only for testing and for presentation mode.
     * it sets your current location in the stack to some specific place
     *
     * @param location the index in the array you go to.
     */
    public void setStackLocation(int location){
        cardNum = location;
    }

    /**
     * This method is only for testing.
     * returns the length of the ChanceCard array.
     * @return
     */
    protected int getStackLength(){
        return chanceCards.length;
    }

    /**
     * Method to shuffle ChanceCards in array.
     * @param array This is the array to be shuffled.
     */
    public void shuffleCards(ChanceCard[] array) {
        int n = array.length;
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {

            int randomValue = i + random.nextInt(n - i);
            ChanceCard randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }
    }
}
