package Domain.GameElements.Entities;

import java.util.Random;

import Domain.GameElements.Entities.Chancecard.*;
import Domain.GameElements.Fields.Field;
import Domain.GameElements.Fields.Ownable.ShippingField;

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
    private ChanceCardStack() {
        cardNum = 0;
    }

    public void initializeCards(Player[] players, Field[] fields){
        chanceCards = new ChanceCard[]{
                new MoveChanceCard(-3, "Ryk tre felter tilbage."),
                new MoveChanceCard(-3, "Ryk tre felter tilbage."),
                new MoveChanceCard(3, "Ryk tre felter frem."),
                new BirthdayCard(500, players, "De har lagt penge ud til et sammenskudsgilde. " +
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
                new TransactionCard(-1000, "De har kørt frem for \"fuldt stop\". Betal kr. 1.000.),"),
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
                new TaxCard(800, 2300, "Ejendomsskatterne er steget. " +
                        "Ekstraudgiftere er: kr. 800 pr. hus, kr. 2.300 pr. hotel."),
                new TaxCard(500, 2000, "Oliepriserne er steget og " +
                        "De skal betale: kr.500 pr. hus kr. 2.000 pr. hotel."),
                new MoveToChanceCard(32, "Ryk frem til Vimmelskaftet. Hvis De passerer \"START\", " +
                        "indkassér da kr. 4.000"),
                new MoveToChanceCard(0, "Ryk frem til \"START\"."),
                new MoveToChanceCard(24, "Ryk frem til Grønningen. Hvis De passerer \"START\", " +
                        "indkassér da kr. 4.000."),
                new MoveToChanceCard(19, "Ryk frem til Strandvejen. Hvis De passerer \"START\", " +
                        "indkassér da kr. 4.000"),
                new MoveToChanceCard(11, "Ryk frem til Frederiksberg Allé. Hvis De passerer \"START\", " +
                        "indkassér da kr. 4.000."),
                new MoveToChanceCard(0, "Ryk frem til \"START\"."),
                new MoveToJailChanceCard(10, "Gå i fængsel. Selv om de passerer \"START\"," +
                        " indkasserer De ikke kr. 4.000."),
                new MoveToJailChanceCard(10, "Gå i fængsel. Selv om de passerer \"START\"," +
                        " indkasserer De ikke kr. 4.000."),
                new MoveToChanceCard(39, "Tag ind på Rådhuspladsen."),
                new MoveToChanceCard(15, "Tag med Mols-Linien. Flyt brikken frem, og hvis " +
                        "De passerer \"START\", indkassér da kr. 4.000."),
                new GetOutOfJailCard("I andledning af kongens fødselsdag benådes de herved for fængsel. " +
                        "Dette kort kan opbevares, indtil De får brug for det eller De kan sælge det."),
                new GetOutOfJailCard("I andledning af kongens fødselsdag benådes de herved for fængsel. " +
                        "Dette kort kan opbevares, indtil De får brug for det eller De kan sælge det."),
                new MoveToNearestChanceCard(ShippingField.class, fields,"Tag med den nærmeste færge. " +
                        "Flyt brikken frem, og hvis De passerer \"START\", indkassér da kr. 4.000."),
                new MoveToNearestChanceCard(ShippingField.class, fields, true, "Ryk brikken frem " +
                        "til det nærmeste rederi og betal ejeren to gange den leje, han ellers er berretiget til. " +
                        "Hvis selskabet ikke ejes af nogen, kan De købe det af banken."),
                new MoveToNearestChanceCard(ShippingField.class, fields, true, "Ryk brikken frem " +
                        "til det nærmeste rederi og betal ejeren to gange den leje, han ellers er berretiget til. " +
                        "Hvis selskabet ikke ejes af nogen, kan De købe det af banken."),
                new MatadorLegateChanceCard(40000, "De modtager \"Matador-legatet for værdigt " +
                        "trængende\" på kr. 40.000. Ved værdigt trængende forstås, at Deres formue, dvs. Deres " +
                        "kontante penge + skøder + bygninger, ikke overstiger kr. 15.000.")
        };
    }

    //Premade arbitrary arrangement of the chancecards for presentationMode
    public void arrangeCards(Player[] players, Field[] fields){
        chanceCards = new ChanceCard[]{
                new MoveToJailChanceCard("Gå i fængsel. Selv om de passerer \"START\"," +
                        " indkasserer De ikke kr. 4.000."),
                new BirthdayCard(500, players, "De har lagt penge ud til et sammenskudsgilde. " +
                        "Mærkværdigvis betaler alle straks. Modtag fra hver medspiller kr. 500."),
                new MoveToNearestChanceCard(ShippingField.class, fields,"Tag med den nærmeste færge. " +
                        "Flyt brikken frem, og hvis De passerer \"START\", indkassér da kr. 4.000."),
                new GetOutOfJailCard("I andledning af kongens fødselsdag benådes de herved for fængsel. " +
                        "Dette kort kan opbevares, indtil De får brug for det eller De kan sælge det."),
                new MoveToChanceCard(32, "Ryk frem til Vimmelskaftet. Hvis De passerer \"START\", " +
                        "indkassér da kr. 4.000")

        };
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
            cardNum = 0;
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
     */
    public void shuffleCards() {
        int n = chanceCards.length;
        Random random = new Random();

        for (int i = 0; i < chanceCards.length; i++) {

            int randomValue = i + random.nextInt(n - i);
            ChanceCard randomElement = chanceCards[randomValue];
            chanceCards[randomValue] = chanceCards[i];
            chanceCards[i] = randomElement;
        }
    }
}
