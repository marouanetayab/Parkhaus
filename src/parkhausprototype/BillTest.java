package parkhausprototype;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillTest {
	Bill bill;

	@Before
	public void setUp() {
		bill = new Bill("test", 1, 1);
	}

	@Test
	public void getFahrzeugName() {
		assertEquals("test", bill.getFahrzeugName());
	}

	@Test
	public void setFahrzeugName() {
		assertEquals("test", bill.getFahrzeugName());
		bill.setFahrzeugName("test2");
		assertEquals("test2", bill.getFahrzeugName());
	}

	@Test
	public void getKosten() {
		assertEquals(1.0, bill.getKosten(), 0);
	}

	@Test
	public void setKosten() {
		assertEquals(1.0, bill.getKosten(), 0);
		bill.setKosten(2.0);
		assertEquals(2.0, bill.getKosten(), 0);
	}

	@Test
	public void getZeit() {
		assertEquals(1.0, bill.getZeit(), 0);
	}

	@Test
	public void setZeit() {
		assertEquals(1.0, bill.getZeit(), 0);
		bill.setZeit(2.0);
		assertEquals(2.0, bill.getZeit(), 0);
	}
}
