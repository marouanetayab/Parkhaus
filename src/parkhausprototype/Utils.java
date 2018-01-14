package parkhausprototype;

import de.vandermeer.asciitable.AsciiTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

public class Utils {
	public static Double round(Number src, int decimalPlaces) {

		return Optional.ofNullable(src)
			.map(Number::doubleValue)
			.map(BigDecimal::new)
			.map(dbl -> dbl.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP))
			.map(BigDecimal::doubleValue)
			.orElse(null);
	}

	public static String formatMoney(double amount) {
		return String.format("%.2f", amount);
	}


	public static String formatTime(double time) {
		int minutes = (int) time;
		int seconds = (int) Math.round((time - minutes) * 100);
		return String.format("%02d:%02d", minutes, seconds);
	}

	public static String buildTableOutputBills(Collection<Bill> bills) {
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("Fahrzeug", "Kosten in €", "Parkzeit");
		at.addRule();
		for (Bill bill : bills) {
			at.addRow(bill.getFahrzeugName(), Utils.formatMoney(bill.getKosten()), Utils.formatTime(bill.getZeit()));
			at.addRule();
		}
		return at.render();
	}

	public static String buildTableOutputPlaces(Fahrzeug[] fahrzeuge) {
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("Fahrzeugname", "Platznummer");
		at.addRule();
		int i = 0;
		for (Fahrzeug fahrzeug : fahrzeuge) {
			at.addRow((fahrzeug == null) ? "LEER" : fahrzeug.kfz, i++);
			at.addRule();
		}
		return at.render();
	}

	public static String readFileToString(String fileName) {
		return readFileToString(new File(fileName));
	}

	public static String readFileToString(File file) {
		try {
			byte[] data = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			//noinspection ResultOfMethodCallIgnored
			fis.read(data);
			fis.close();
			return new String(data, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static boolean writeStringToFile(String filename, String content) {
		return Utils.writeStringToFile(new File(filename), content);
	}

	public static boolean writeStringToFile(File file, String content) {
		try (PrintWriter out = new PrintWriter(file)) {
			out.println(content);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}



	public static String getDateTimeString(LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(time);
	}

	public static LocalDateTime getDateTimeFromString(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(time, formatter);
	}
}
