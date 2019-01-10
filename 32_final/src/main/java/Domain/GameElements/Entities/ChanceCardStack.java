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
                new MoveChanceCard(-3, "Ryk tre felter tilbage."),
                new MoveChanceCard(-3, "Ryk tre felter tilabge."),
                new MoveChanceCard(3,"Ryk tre felter frem."),
                new BirthdayCard(500,players,"De har lagt penge ud til et sammenskudsgilde. " +
                        "Mærkværdigvis betaler alle straks. Modtag fra hver medspiller kr. 500."),
                new BirthdayCard(200, players, "Det er Deres fødselsdag. Modtag af hver " +
                        "medspiller kr. 200."),
                new BirthdayCard(500, players, "De skal holde familiefest og får et tilskud " +
                        "fra hver medspiller på kr. 500."),
                new TransactionCard(1000, "De havde en række med elleve rigtige i tipning. " +
                        "Modtag kr. 1.000."),
                new TransactionCard(3000, "Kommunen har eftergivet kvartals skat. Hæv i " +
                        "banken kr. 3.000."),
                new TransactionCard(1000, "De har solgt nogle gamle møbler på auktion. " +
                        "Modtag kr. 1.000."),
                new TransactionCard(-200, "Betal kr. 200 for levering af to kasser øl."),
                new TransactionCard(-200, "De har været en tur i udlandet og har haft for mange " +
                        "cigaretter med hjem. Betal told kr. 200."),
                new TransactionCard(-1000, "Betal Deres bilforsikring - kr. 1.000."),
                new TransactionCard(-2000, "De har modtaget Deres tandlægeregning. Betal kr. 2.000. "),
                new TransactionCard(500, "De har vundet i Klasselotteriet. Modtag kr. 500."),
                new TransactionCard(500, "De har vundet i Klasselotteriet. Modtag kr. 500."),
                new TransactionCard(-200, "De har fået en parkeringsbøde. Betal kr. 200 i bøde."),
                new TransactionCard(-300, "Betal for vognvask og smøring kr. 300."),
                new TransactionCard(-1000, "De har kørt frem for FULDT STOP. Betal kr. 1.000.),"),
                new TransactionCard(1000, "Deres præmieobligation er udtrukket. De modtager kr. " +
                        "1.000. af banken."),
                new TransactionCard(1000, "Deres præmieobligation er udtrukket. De modtager kr. " +
                        "1.000 af banken."),
                new TransactionCard(-3000, "Betal kr. 3.000 for reparation af Deres vogn."),
                new TransactionCard(1000, "Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1.000."),
                new TransactionCard(200, "Værdien af egen avl fra nyttehaven udgør kr. 200, som " +
                        "De modtager af banken."),
                new TransactionCard(1000, "De modtager Deres aktieudbytte. Modtag kr. 1.000 af banken."),
                new TransactionCard(-1000, "De har købt 4 nye dæk til Deres vogn. Betal kr. 1.000."),
                new TransactionCard(-3000, "Betal kr. 3.000 for reparation af Deres vogn."),
                new TransactionCard(1000, "Modtag udbytte af Deres aktier - kr. 1.000."),
                new TransactionCard(1000, "Modtag udbytte af Deres aktier - kr. 1.000."),







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
