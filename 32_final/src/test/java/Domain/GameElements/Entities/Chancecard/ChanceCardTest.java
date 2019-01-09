package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChanceCardTest {

    /**
     * A test for the MoveChanceCard class.
     *
     * testing:
     * positive value
     * negative value
     * positive value over start
     * negative value over start
     */
    @Test
    void moveChanceCardTest() {
        MoveChanceCard positiveCard = new MoveChanceCard(3, "text");
        MoveChanceCard negativeCard = new MoveChanceCard(-3, "text");

        Player p = new Player("p1");
        p.setPos(10);
        p.getAccount().changeScore(-p.getAccount().getScore());


        positiveCard.action(p);
        assertEquals(13, p.getPos());
        negativeCard.action(p);
        assertEquals(10, p.getPos());

        p.setPos(38);
        positiveCard.action(p);
        assertEquals(1, p.getPos());
        assertTrue(p.getAccount().getScore() > 0);
        negativeCard.action(p);
        assertEquals(38, p.getPos());
    }


    @Test
    void moveToChanceCardTest() {
        MoveToChanceCard normalCard = new MoveToChanceCard(20, "text");
        MoveToChanceCard overStartCard = new MoveToChanceCard(3, "text");

        Player p = new Player("p1");
        p.setPos(10);
        p.getAccount().changeScore(-p.getAccount().getScore());

        normalCard.action(p);
        assertEquals(20, p.getPos());
        overStartCard.action(p);
        assertEquals(3, p.getPos());
        assertTrue(p.getAccount().getScore() > 0);

    }
}