package main.java.TechnicalServices.GameLogic;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {
    GameLogic gl = new GameLogic();
    @org.junit.jupiter.api.Test
    void propertyPrice() {
        assertEquals(gl.propertyPrice(33),6000 );
    }

    @org.junit.jupiter.api.Test
    void chanceCardValue() {
        assertEquals(gl.chanceCardValue(18), 1000);
    }
}