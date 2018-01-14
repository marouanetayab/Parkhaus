package parkhausprototype;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParkhausRunTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	private String fInput;

	private String fExpected;

	public ParkhausRunTest(String task) {
		String rootFolder = "test/runner";
		fInput = rootFolder + "/input/" + task + ".txt";
		fExpected = rootFolder + "/output/" + task + ".txt";
	}

	@Parameters(name = "{0}")
	public static ArrayList<String> data() {
		ArrayList<String> list = new ArrayList<>();
		list.add("exit");
		list.add("help");
		list.add("showbills");
		list.add("showplaces");
		list.add("park");
		list.add("numberPark");
		list.add("unpark");
		list.add("numberUnpark");
		list.add("expand");
		list.add("free");
		list.add("sales");
		list.add("allsales");
		list.add("pps");
		return list;
	}

	@Test
	public void test() throws IOException {
		FileInputStream fis = new FileInputStream(new File(fInput));
		System.setIn(fis);
		ParkhausRun.main(new String[0]);
		String expected = getStartText() + Utils.readFileToString(fExpected);
		assertEquals(expected.trim(), outContent.toString().trim());
	}

	private String getStartText() {
		return "Wilkommen zum Virtuosen Parkhaus:\n" +
			"Folgende Befehle stehen zur Verfügung\n" +
			"┌────────────┬──────────────────────────────────────────────────────────┐\n" +
			"│help        │um diese Nachricht zu sehen                               │\n" +
			"│exit        │Programm beenden                                          │\n" +
			"│showbills   │um den Rechnungsverlauf zu sehen                          │\n" +
			"│showplaces  │um ein Überblick über die Parkplätze zu kriegen           │\n" +
			"│park        │um ein neues Fahrzeug zu parken                           │\n" +
			"│numberPark  │um ein neues Fahrzeug auf einem bestimmten Platz zu parken│\n" +
			"│unpark      │um ein Fahrzeug raus zu fahren                            │\n" +
			"│numberUnpark│um einen bestimmten Platz frei zu machen                  │\n" +
			"│expand      │um mehr Parkplaetze zu erschaffen                         │\n" +
			"│free        │um die Anzahl freier Plätze zu erhalten                   │\n" +
			"│sales       │um den bisherigen Umsatz zu sehen                         │\n" +
			"│allsales    │um alle Umsätze zu sehen                                  │\n" +
			"│pps         │um die Parkgebühren pro Sekunde anzupassen                │\n" +
			"└────────────┴──────────────────────────────────────────────────────────┘\n";
	}
}
