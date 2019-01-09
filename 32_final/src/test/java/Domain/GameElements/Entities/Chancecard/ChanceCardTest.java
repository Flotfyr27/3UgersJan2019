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

    /**
     * tests the MoveToChanceCard
     *
     * testing:
     * a field in front of player
     * a field on the other side of start
     * a field on the other side of start for which you should not get paid to go to.
     */
    @Test
    void moveToChanceCardTest() {
        MoveToChanceCard normalCard = new MoveToChanceCard(20, "text");
        MoveToChanceCard overStartCard = new MoveToChanceCard(3, "text");
        MoveToChanceCard overStartNoMoneyCard = new MoveToChanceCard(3, false, "text");

        Player p = new Player("p1");
        p.setPos(10);
        p.getAccount().changeScore(-p.getAccount().getScore());

        normalCard.action(p);
        assertEquals(20, p.getPos());
        overStartCard.action(p);
        assertEquals(3, p.getPos());
        assertTrue(p.getAccount().getScore() > 0);
        p.setPos(20);
        p.getAccount().changeScore(-p.getAccount().getScore());
        overStartNoMoneyCard.action(p);
        assertEquals(3, p.getPos());
        assertEquals(0, p.getAccount().getScore());
    }

    @Test
    void moveToNearestChanceCardTest(){
        
    }

    @Test
    void transactionCardTest(){
        TransactionCard positiveCard = new TransactionCard(100, "text");
        TransactionCard negativeCard = new TransactionCard(-50, "text");

        Player p = new Player("p1");
        p.setPos(10);
        p.getAccount().changeScore(-p.getAccount().getScore());

        positiveCard.action(p);
        assertEquals(100, p.getAccount().getScore());
        negativeCard.action(p);
        assertEquals(50, p.getAccount().getScore());
    }


    @Test
    void taxCardTest(){

    }

    @Test
    void birthdayCardTest(){
        int startMoney = 200;
        int giftSize = 100;

        Player[] players = {new Player("p1"), new Player("p2"),
                new Player("p3"), new Player("p4"), new Player("p5")};
        Player p = players[0];
        BirthdayCard card = new BirthdayCard(giftSize, players, "text");

        for (Player player : players) {
            player.getAccount().changeScore(startMoney - player.getAccount().getScore());
        }

        card.action(p);
        assertEquals((startMoney + (giftSize * (players.length-1))), p.getAccount().getScore());
        for (int i = 1; i < players.length; i++) { //checks the value of all players except p
            assertEquals((startMoney-giftSize), players[i].getAccount().getScore());
        }
    }
}