package parkhausprototype;

import de.vandermeer.asciitable.AsciiTable;

import java.math.BigDecimal;
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
		int minutes = (int) time % 24;
		int seconds = (int) Math.round((time - minutes) * 100) % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	public static String buildTableOutputBills(Collection<Bill> bills) {
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("Fahrzeug", "Kosten in €", "Parkzeit");
		at.addRule();
		for (Bill bill : bills) {
			at.addRow(bill.getFahrzeugName(),Utils.formatMoney(bill.getKosten()), Utils.formatTime(bill.getZeit()));
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
}
