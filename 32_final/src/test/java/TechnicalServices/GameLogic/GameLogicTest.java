package TechnicalServices.GameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {
    GameLogic gl = new GameLogic();
    Values val = new Values();
    @Test
    void propertyPrice() {
        assertEquals(val.propertyPrice(33),6000 );
    }

    @Test
    void chanceCardValue() {
        assertEquals(val.chanceCardValue(18), 1000);
    }

}