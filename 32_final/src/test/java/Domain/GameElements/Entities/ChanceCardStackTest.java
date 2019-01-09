package Domain.GameElements.Entities;

import Domain.GameElements.Entities.Chancecard.ChanceCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChanceCardStackTest {

    /**
     * tests the getInstance method to ensure it does function as a singleton
     *
     * testing:
     * only one instance will ever be returned
     */
    @Test
    void getStackInstance() {
        ChanceCardStack stack = ChanceCardStack.getStackInstance();
        ChanceCardStack stack2 = ChanceCardStack.getStackInstance();
        assertEquals(stack, stack2);
    }

    /**
     * tests the hasNext() method
     *
     * testing:
     * when there is a next card
     * when there isn't a next card
     */
    @Test
    void hasNext() {
        ChanceCardStack stack = ChanceCardStack.getStackInstance();
        assertEquals(true, stack.next());

        stack.setStackLocation(stack.getStackLength()-1);
        assertEquals(false, stack.next());
    }

    /**
     * tests the next() and getCurrent method
     *
     * testing:
     * getCurrent returns right type
     * next returns right type
     * next returns right type when located at end of array
     *
     * should add test for specific cards, but that requires they are made first and that a non random mode is made
     *
     */
    @Test
    void nextAndGetCurrent() {
        ChanceCardStack stack = ChanceCardStack.getStackInstance();
        stack.setStackLocation(0);

        assertEquals(ChanceCard.class, stack.getCurrent().getClass());
        assertEquals(ChanceCard.class, stack.next().getClass());

        stack.setStackLocation(stack.getStackLength()-1);
        assertEquals(ChanceCard.class, stack.next().getClass());
    }



}