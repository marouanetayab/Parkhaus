package parkhausprototype;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ParkhausTest {
	private Parkhaus parkhaus;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Rule
	public ExpectedException thrown = ExpectedException.none();


	@Before
	public void setUp() {
		parkhaus = new Parkhaus(10);
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void getNextPlace() {
		assertEquals(0, parkhaus.getNextPlace());
		parkhaus.park(new Fahrzeug("test1"));
		assertEquals(1, parkhaus.getNextPlace());
	}

	@Test
	public void park() {
		assertEquals(10, parkhaus.freePlaces());
		parkhaus.park(new Fahrzeug("test1"));
		assertEquals(9, parkhaus.freePlaces());
	}

	@Test
	public void parkOnPlace() throws Exception {
		Fahrzeug fahrzeug = new Fahrzeug("testOnPlace");
		parkhaus.parkOnPlace(fahrzeug, 9);
		assertEquals(fahrzeug, parkhaus.plaetze[9]);
	}

	@Test
	public void unpark() throws Exception {
		Fahrzeug fahrzeug = new Fahrzeug("testUnpark");
		parkhaus.park(fahrzeug);
		assertEquals(fahrzeug, parkhaus.plaetze[0]);
		assertTrue(fahrzeug.parked);
		parkhaus.unpark("testUnpark");
		assertNull(parkhaus.plaetze[0]);
		assertFalse(fahrzeug.parked);

	}

	@Test
	public void unpark1() {
		Fahrzeug fahrzeug = new Fahrzeug("testUnpark");
		parkhaus.park(fahrzeug);
		assertEquals(fahrzeug, parkhaus.plaetze[0]);
		assertTrue(fahrzeug.parked);
		try {
			parkhaus.unpark(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNull(parkhaus.plaetze[0]);
		assertFalse(fahrzeug.parked);
	}

	@Test
	public void unparkException() throws Exception {
		Parkhaus exceptionParkhaus = new Parkhaus(5);
		thrown.expect(Exception.class);
		thrown.expectMessage("Ungültige Platznummer!");
		exceptionParkhaus.unpark(9);
	}

	@Test
	public void unpark2() throws Exception {
		Fahrzeug fahrzeug = new Fahrzeug("testUnpark");
		parkhaus.park(fahrzeug);
		assertEquals(fahrzeug, parkhaus.plaetze[0]);
		assertTrue(fahrzeug.parked);
		parkhaus.unpark(fahrzeug);
		assertNull(parkhaus.plaetze[0]);
		assertFalse(fahrzeug.parked);
	}

	@Test
	public void freePlaces() throws Exception {
		assertEquals(10, parkhaus.freePlaces());
		parkhaus.park(new Fahrzeug("testFreePlaces"));
		assertEquals(9, parkhaus.freePlaces());
		parkhaus.unpark("testFreePlaces");
		assertEquals(10, parkhaus.freePlaces());
	}

	@Test
	public void createMorePlaces() {
		assertEquals(10, parkhaus.freePlaces());
		parkhaus.createMorePlaces(10);
		assertEquals(20, parkhaus.freePlaces());
	}

	@Test
	public void createMorePlacesWithParkedCars() throws Exception {
		assertEquals(10, parkhaus.freePlaces());
		parkhaus.park(new Fahrzeug("moreSpace1"));
		parkhaus.park(new Fahrzeug("moreSpace2"));
		assertEquals(8, parkhaus.freePlaces());
		parkhaus.createMorePlaces(10);
		assertEquals(18, parkhaus.freePlaces());
		parkhaus.unpark("moreSpace1");
		parkhaus.unpark("moreSpace2");
		assertEquals(20, parkhaus.freePlaces());
	}

	@Test
	public void getUmsatz() throws Exception {
		assertEquals(0, parkhaus.getUmsatz(), 0);
		parkhaus.park(new Fahrzeug("test"));
		TimeUnit.SECONDS.sleep(1);
		parkhaus.unpark("test");
		assertEquals(Utils.round(parkhaus.getPricePerSek(), 2), parkhaus.getUmsatz(), 0);
	}

	@Test
	public void setUmsatz() {
	}

	@Test
	public void getPricePerSek() {
	}

	@Test
	public void setPricePerSek() {
	}

	@Test
	public void getBills() {
	}

	@Test
	public void addBill() {
	}
}
