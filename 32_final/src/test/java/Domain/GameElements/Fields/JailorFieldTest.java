package Domain.GameElements.Fields;

import Domain.GameElements.Entities.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class JailorFieldTest {

    @Test
    void landOnAction() {
        Player player = new Player("testPlayer");
        assertEquals(0,player.getJailCards());
        player.addJailCards();
        assertEquals(1, player.getJailCards());

        JailerField jf = new JailerField("jailorFieldTest", "test", Color.BLUE);
        jf.landOnAction(player);
        assertEquals(0, player.getJailCards());
        assertEquals(0, player.getPos());
        jf.landOnAction(player);
        assertEquals(10, player.getPos());


    }
}