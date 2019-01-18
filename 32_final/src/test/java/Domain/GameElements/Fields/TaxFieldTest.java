package Domain.GameElements.Fields;

import Domain.GameElements.Entities.Player;
import UI.GUI.GuiHandler;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TaxFieldTest {


    @Test
    void landOnAction() {
        Player player = new Player("testPlayer");
        assertEquals(30000, player.getAccount().getScore());

        TaxField tf = new TaxField("taxTest", "test", Color.BLUE, 1);
        tf.landOnAction(player);
        assertEquals(28000, player.getAccount().getScore());










    }
}