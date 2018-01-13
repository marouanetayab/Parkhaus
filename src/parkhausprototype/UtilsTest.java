package parkhausprototype;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

	@Test
	public void round() {
		assertEquals(2.35, Utils.round(2.34563423213, 2), 0.01);
		assertEquals(2.00, Utils.round(2.10, 0), 0.01);
		assertEquals(3, Utils.round(2.50, 0), 0.01);
		assertEquals(3.142, Utils.round(Math.PI, 3), 0.01);
	}
}
