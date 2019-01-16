package Domain.GameElements.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DieTest {

    @Test
    void roll() {
        Die die = new Die();
        int nr1 = 1, nr2= 2, nr3 = 3, nr4 = 4, nr5 = 5, nr6 = 6;
        //Rolls die 600000 times and tracks the roll value
        for(int i = 0; i < 600000; i++){
            int value = die.Roll();
            if(value == 1){
                nr1++;
            }if (value ==2 ){
                nr2++;
            }if(value == 3){
                nr3++;
            }if (value ==4 ) {
                nr4++;
            }if(value == 5){
                nr5++;
            }if (value ==6 ) {
                nr6++;
            }
        }
        System.out.println("Antal 1'ere: " + nr1);
        System.out.println("Antal 2'ere: " + nr2);
        System.out.println("Antal 3'ere: " + nr3);
        System.out.println("Antal 4'ere: " + nr4);
        System.out.println("Antal 5'ere: " + nr5);
        System.out.println("Antal 6'ere: " + nr6);

        //Examines how accurate the dice is
        assertEquals(100000, nr1, 500);
        assertEquals(100000, nr2,500);
        assertEquals(100000, nr3,500);
        assertEquals(100000, nr4,500);
        assertEquals(100000, nr5,500);
        assertEquals(100000, nr6,500);


    }
}