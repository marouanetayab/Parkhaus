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

	@Test
	public void formatMoney() {
		assertEquals("2.03", Utils.formatMoney(2.03));
		assertEquals("0.03", Utils.formatMoney(0.03));
		assertEquals("0.00", Utils.formatMoney(0.0));
	}

	@Test
	public void formatTime() {
		assertEquals("00:00", Utils.formatTime(0.0));
		assertEquals("00:02", Utils.formatTime(0.02));
		assertEquals("00:20", Utils.formatTime(0.2));
		assertEquals("01:20", Utils.formatTime(1.2));
		assertEquals("23:20", Utils.formatTime(23.2));
	}

	@Test
	public void readFileToString() {
		assertEquals("Content\n\nmultiple Strings\n\nlong", Utils.readFileToString("test/utils/readtoString.txt"));
	}
}
