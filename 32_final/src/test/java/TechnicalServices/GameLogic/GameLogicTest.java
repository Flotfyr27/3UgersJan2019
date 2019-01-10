package TechnicalServices.GameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {
    GameLogic gl = new GameLogic();
    @Test
    void propertyPrice() {
        assertEquals(gl.propertyPrice(33),6000 );
    }

    @Test
    void chanceCardValue() {
        assertEquals(gl.chanceCardValue(18), 1000);
    }

}