package main.java.TechnicalServices.GameLogic;

public class Values {
    /**
     * This method returns the price of a property, so if you want the price for field number 9, you type propertyPrice(9)
     * @param i The position of the property on the board
     * @return The price of the property, given by its position on the board
     */
    public static int propertyPrice(int i){
        int price = 0;
        switch(i) {
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
     * @return
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
}
/*TODO Make methods for all property, which provides the current rent (streets, faeries and breweries)*/