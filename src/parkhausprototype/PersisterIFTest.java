package parkhausprototype;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersisterIFTest {

	private Persister persister;
	private String fileName;

	@Before
	public void setUp() throws Exception {
		fileName = "test/persister/test.json";
		persister = new Persister(fileName);
	}

	@Test
	public void saveToFile() {
		Parkhaus parkhaus = new Parkhaus(5);
		persister.saveToFile(parkhaus);

		assertEquals("", Utils.readFileToString(fileName));
	}

	@Test
	public void loadFromFile() {
	}
}
