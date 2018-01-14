package parkhausprototype;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class PersisterTest {

	private Persister persister;
	private String fileName;

	@Before
	public void setUp() throws Exception {
		fileName = "test/persister/test.json";
		persister = new Persister(fileName);
		Utils.writeStringToFile(fileName, "");
	}

	@After
	public void tearDown() throws Exception {
		Utils.writeStringToFile(fileName, "");
	}

	@Test
	public void saveToFile() {
		Parkhaus parkhaus = new Parkhaus(5);
		persister.saveToFile(parkhaus);

		assertEquals("{\"size\":5,\"plaetze\":[],\"bills\":[],\"umsatz\":0.0,\"pricePerSek\":0.025}", Utils.readFileToString(fileName).trim());
	}

	@Test
	public void saveToFileWithParkedCars() {
		Parkhaus parkhaus = new Parkhaus(5);
		LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
		parkhaus.park(new Fahrzeug("test1"));
		parkhaus.park(new Fahrzeug("test2"));
		parkhaus.park(new Fahrzeug("test3"));
		persister.saveToFile(parkhaus);
		assertEquals("{\"size\":5,\"plaetze\":[{\"kfz\":\"test1\",\"begin\":\"" + Utils.getDateTimeString(localDateTime) + "\",\"parkNr\":0},{\"kfz\":\"test2\",\"begin\":\"" + Utils.getDateTimeString(localDateTime) + "\",\"parkNr\":1},{\"kfz\":\"test3\",\"begin\":\"" + Utils.getDateTimeString(localDateTime) + "\",\"parkNr\":2}],\"bills\":[],\"umsatz\":0.0,\"pricePerSek\":0.025}", Utils.readFileToString(fileName).trim());
	}

	@Test
	public void loadFromFile() {
		Utils.writeStringToFile(fileName, "{\"size\":5,\"plaetze\":[],\"bills\":[],\"umsatz\":0.0,\"pricePerSek\":0.025}");
		assertEquals(persister.loadFromFile(), new Parkhaus(5));
	}
}
