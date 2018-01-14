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
			"Folgende Befehle stehen zur Verfuegung\n" +
			"help \t\t um diese Nachricht zu sehen\n" +
			"exit \t\t um die Simulation zu verlassen\n" +
			"showbills \t um den Rechnungsverlauf zu sehen\n" +
			"showplaces \t um ein �berblick �ber die Parkpl�tze zu kriegen\n" +
			"park \t\t um ein neues Fahrzeug zu parken\n" +
			"numberPark \t um ein neues Fahrzeug auf einem bestimmten Platz zu parken\n" +
			"unpark \t\t um ein Fahrzeug raus zu fahren\n" +
			"numberUnpark \t um einen bestimmten Platz frei zu machen\n" +
			"expand \t\t um mehr Parkplaetze zu erschaffen\n" +
			"free \t\t um die Anzahl freier Pl�tze zu erhalten\n" +
			"sales \t\t um den bisherigen Umsatz zu sehen\n" +
			"allsales \t um alle Ums�tze zu sehen\n" +
			"pps \t\t um die Parkgeb�hren pro Sekunde anzupassen\n";
	}
}
