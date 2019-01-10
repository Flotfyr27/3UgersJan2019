package Domain.GameElements.Fields.Ownable;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PropertyFieldTest {

    @Test
    void getHouses() {
        PropertyField pf = new PropertyField("Test", "Subtext", null, 4000, 1000);
        assertEquals(0, pf.getHouses());
    }
    @Test
    void addHouse(){
        PropertyField pf = new PropertyField("Test", "Subtext", null, 4000, 1000);
        assertEquals(0, pf.getHouses());
        pf.addHouse();
        assertEquals(1, pf.getHouses());
    }

    @Test
    void removeHouse(int value){
        PropertyField pf = new PropertyField("Test", "Subtext", null, 4000, 1000);
        assertEquals(0, pf.getHouses());
        pf.addHouse();
        pf.addHouse();
        assertEquals(2, pf.getHouses());
        pf.removeHouse(2);
        assertEquals(0, pf.getHouses());
    }

    @Test
    void getHotel() {
    }

    @Test
    void getWorth() {
    }

    @Test
    void sell() {
    }

    @Test
    void buy() {
    }

    @Test
    void pawn() {
    }

    @Test
    void payRent() {
    }
}