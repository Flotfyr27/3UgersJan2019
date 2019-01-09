package main.java.TechnicalServices.GameLogic;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {
    GameLogic gl;
    @org.junit.jupiter.api.Test
    void propertyPrice() {
        assertEquals(gl.propertyPrice(10),6000 );
    }

    @org.junit.jupiter.api.Test
    void chanceCardValue() {
    }
}