package parkhausprototype;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class FahrzeugTest {
    Fahrzeug fahrzeug;

    @Before
    public void setUp() {
        fahrzeug = new Fahrzeug("test1");
    }

    @Test
    public void reset() {
        fahrzeug.park(1);
        fahrzeug.reset();
        assertEquals(-1, fahrzeug.parkNR);
        assertFalse(fahrzeug.parked);
    }

    @Test
    public void park() {
        fahrzeug.park(1);
        assertEquals(1, fahrzeug.parkNR);
        assertTrue(fahrzeug.parked);
        assertEquals(LocalDateTime.now().withNano(0), fahrzeug.begin);
    }

    @Test
    public void unpark() throws InterruptedException {
        fahrzeug.park(1);
        TimeUnit.SECONDS.sleep(1);
        fahrzeug.unpark();
        assertEquals(LocalDateTime.now().withNano(0), fahrzeug.end);
        assertEquals(1, fahrzeug.duration, 0);
    }
}
