package TechnicalServices.GameLogic;

import Domain.GameElements.Entities.DiceTray;

public class Values {
    private static DiceTray dicetray;;
    public Values(){
        DiceTray dicetray = new DiceTray();
    }



    /**
     * This method returns the price of a property, so if you want the price for field number 9, you type propertyPrice(9)
     * @param i The position of the property on the board
     * @return The price of the property, given by its position on the board
     */
    public static int propertyPrice(int i) {
        int price = 0;
        switch (i) {
            case 2:
                price = 1200;
                break;
            case 4:
                price = 1200;
                break;
            case 6:
                price = 4000;
                break;
            case 7:
                price = 2000;
                break;
            case 9:
                price = 2000;
                break;
            case 10:
                price = 2400;
                break;
            case 12:
                price = 2800;
                break;
            case 13:
                price = 3000;
                break;
            case 14:
                price = 2800;
                break;
            case 15:
                price = 3200;
                break;
            case 16:
                price = 4000;
                break;
            case 17:
                price = 3600;
                break;
            case 19:
                price = 3600;
                break;
            case 20:
                price = 4000;
                break;
            case 22:
                price = 4400;
                break;
            case 24:
                price = 4400;
                break;
            case 25:
                price = 4800;
                break;
            case 26:
                price = 4000;
                break;
            case 27:
                price = 5200;
                break;
            case 28:
                price = 5200;
                break;
            case 29:
                price = 3000;
                break;
            case 30:
                price = 5600;
                break;
            case 32:
                price = 6000;
                break;
            case 33:
                price = 6000;
                break;
            case 35:
                price = 6400;
                break;
            case 36:
                price = 4000;
                break;
            case 38:
                price = 7000;
                break;
            case 40:
                price = 8000;
                break;

        }
        return price;
    }

    /**
     * [0]: Betal 3000 kr for reperation af vogn
     * [1]: De modtager en tandlægeregning, betal 2000 kr
     * [2]: De har kørt frem for fuldt stop. Betal 1000 kr i bøde
     * [3]: De har købt fire nye dæk, til deres vogn betal 1000 kr
     * [4]: Betal deres bilforsikring - 1000 kr
     * [5]: Betal for vognvask og smøring - 300 kr
     * [6]: Betal kr. 200 for levering af 2 kasser øl
     * [7]: Betal told for cigaretter - 200 kr
     * [8]: Betal parkeringsbøde 200 kr
     * [9]: Værdien af egen avl udgør 200, modtages af banken
     * [10]: Hip hurra; fødselsdag modtag fra hver spiller 200 kr                     *
     * [11]: Sammenskudsgilde, alle betaler 500 kr                                    *
     * [12]: Klasselotteriet, modtag 500 kr
     * [13]: Familiefest, få tilskud fra hver spiller på 500 kr                       *
     * [14]: Solgt gamle møbler, modtag 1000 kr
     * [15]: Aktieudbytte, modtag 1000 kr
     * [16]: Gageforhøjelse, modtag 1000 kr
     * [17]: Præmieobligation, modtag 1000 kr
     * [18]: Aktie afkast, modtag 1000 kr
     * [19]: Række med 11 rigtige, modtag 1000 kr
     * [20]: Eftergivet kvartals skal, modtag 3000 kr
     * [21]: Matador legatet, if (getWorth <= 15000 ) modtag 40000 kr
     * [22]&&[23] Oliepriserne stiger betal 500 kr. pr hus og 2000 kr pr. hotel      *
     * [24]&&[25] Ejendomsskatterne stiger betal 800 kr pr. hus og 2300 kr pr. hotel *
     * @param i
     * @return Returns the value effect of the chancecard
     */
    public static int chanceCardValue(int i){
        int value = 0;
        int[] chanceCardValues = {
                -3000, -2000, -1000, -1000, -1000 -300, -200, -200, -200, 200, 200, 500, 500, 500, 1000, 1000, 1000, 1000, 1000, 1000, 3000, 40000, -500, -2000, -800, -2300 /*TODO sørg for at de fire sidste priser er reguleret i henhold til antal huse og hoteller, samt de andre fælles puljer, markeret med stjerne */
        };
        value = chanceCardValues[i];
        return value;
    }

    /**
     * Returns the price for a house, relating to the tier of the property.
     * Tier 1 = 1000
     * Tier 2 = 2000
     * Tier 3 = 3000
     * Tier 4 = 4000
     * @param tier
     * @return
     */
    public static int housePrice(int tier) {
        int price = tier*1000;
        return price;
    }

    /**
     *  @param i
     *  @return A ragged array, that tells the price of the rent for the properties with and without houses/hotels.
     *  [0]: Rent for Rødovrevej
     *  [1]: Rent for Hvidovrevej
     *  [2]: Rent for Scandlines "Helsingør-Helsingborg"
     *  [3]: Rent for Roskildevej
     *  [4]: Rent for Valby Langgade
     *  [5]: Rent for Allégade
     *  [6]: Rent for Fredriksberg Allé
     *  [7]: Rent for Turborg Squash
     *  [8]: Rent for Bülowsvej
     *  [9]: Rent for Gl. Kongevej
     *  [10]: Rent for Mols-Linien
     *  [11]: Rent for Bernstorffsvej
     *  [12]: Rent for Hellerupvej
     *  [13]: Rent for Strandvejen
     *  [14]: Rent for Trianglen
     *  [15]: Rent for Østerbrogade
     *  [16]: Rent for Grønningen
     *  [17]: Rent for Scandlines "Gedser-Rostock"
     *  [18]: Rent for Bredgade
     *  [19]: Rent for Kgs.Nytorv
     *  [20]: Rent for CocaCola
     *  [21]: Rent for Østergade
     *  [22]: Rent for Amagertorv
     *  [23]: Rent for Vimmelskaffet
     *  [24]: Rent for Nygade
     *  [25]: Rent for Scandlines "Rødby-Puttgarden"
     *  [26]: Rent for Frederiksbergade
     *  [27]: Rent for Rådhuspladsen
     */


    public static int rentPrice (int i) {
        int rent =0;
              int rentPrice [][]  =
                {{50, 250, 750, 2250, 4000, 6000},
                {50,250,750,2250,4000,6000},
                {500,1000,2000,4000},
                {100,600,1800,5400,8000,11000},
                {100,600,1800,5400,8000,11000},
                {150,800,2000,6000,9000,12000},
                {200,1000,3000,9000,12500,15000},
                {(100*dicetray.getSum()),(200*dicetray.getSum())},
                {200,1000,3000,9000,12500,15000},
                {250,1250,3750,10000,14000,18000},
                {500,1000,2000,4000},
                {300,1400,4000,11000,15000,19000},
                {300,1400,4000,11000,15000,19000},
                {350,1600,4400,12000,16000,20000},
                {350,1800,5000,14000,17500,21000},
                {350,1800,5000,14000,17500,21000},
                {400,2000,6000,15000,18500,22000},
                {500,1000,2000,4000},
                {450,2200,6600,16000,19500,23000},
                {450,2200,6600,16000,19500,23000},
                {(100*dicetray.getSum()),(200*dicetray.getSum())},
                {500,2400,7200,17000,20500,24000},
                {550,2600,7800,18000,22000,25000},
                {550,2600,7800,18000,22000,25000},
                {600,3000,9000,20000,24000,28000},
                {700,3500,10000,22000,26000,30000},
                {1000,4000,12000,28000,34000,40000}};

    rent = rentPrice[i][i];
return rent;
    }




}
/*TODO Make methods for all property, which provides the current rent (streets, faeries and breweries)*/