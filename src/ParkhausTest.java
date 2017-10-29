import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Parkhaus
 *
 * @author Johannes Heimbach
 * 29.10.17.
 */
public class ParkhausTest {
    Parkhaus parkhouse;
    Fahrzeug mockAuto;

    @Before
    public void setUp() {
        //Todo: Implemennt Parkhaus and init it here;
    }

    /**
     * check if next place counts up, if car gets parked. if car is unparked before. next place is the lowest in list.
     */
    @Test
    public void getNextPlace_firstCarIn_NextPlaceCountsUp() {
        assertEquals(0, parkhouse.getNextPlace());
        parkhouse.park(mockAuto);
        assertEquals(1, parkhouse.getNextPlace());
    }

    /**
     * in a parkhouse with baserate of 3.5 for 1 hour. bill for 2 hours should be 7.00
     */
    @Test
    public void payBill_2HourParking_billIs2TimesBaserate() {
        assertEquals(7.00d, parkhouse.payBill(mockAuto), 0.1);
    }


    /**
     * expected setup: a new Parkhouse has 20 free places
     */
    @Test
    public void freePlaces_ParkhouseWith20Places_countsDownIfCarIsParked() {
        assertEquals(20, parkhouse.freePlaces());
        parkhouse.park(mockAuto);
        assertEquals(19, parkhouse.freePlaces());
    }

}