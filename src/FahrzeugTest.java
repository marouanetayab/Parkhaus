import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Parkhaus
 *
 * @author Johannes Heimbach
 * 29.10.17.
 */
public class FahrzeugTest {
    Fahrzeug auto;

    @Before
    public void setUp() {
        //Todo: implement Fahrzeug and init auto here;
    }

    @Test
    public void getTime_newFahrzeug_emptyTime() {
        //assert standard values, if nothing else is set
        assertEquals(0, auto.getTime().getHours());
        assertEquals(0, auto.getTime().getMinutes());
    }

    @Test
    public void place_newFahrzeug_negativePlaceNumber() {
        // return -1 if no place is given
        assertEquals(-1, auto.place());
    }
}