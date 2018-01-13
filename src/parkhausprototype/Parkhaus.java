package parkhausprototype;

import java.util.Arrays;
import java.util.HashMap;

public class Parkhaus implements ParkhausIF {

	Fahrzeug[] plaetze;
	private HashMap<String, Bill> bills = new HashMap<>();
	double[] wochenumsaetze;
	private double umsatz;
	private double pricePerSek = 0.025;

	@Override
	public double getUmsatz() {
		return umsatz;
	}

	public void setUmsatz(double umsatz) {
		this.umsatz = umsatz;
	}

	public double getPricePerSek() {
		return pricePerSek;
	}

	public void setPricePerSek(double pricePerSek) {
		this.pricePerSek = pricePerSek;
	}

	public HashMap<String, Bill> getBills() {
		return bills;
	}

	public void addBill(String name, double kosten, double zeit) {
		Bill bill = new Bill(name, kosten, zeit);
		bills.put("bill_" + name, bill);
	}

	public void dummy_daten_umsaetze() {
		wochenumsaetze[0] = 532.04;
		wochenumsaetze[1] = 385.32;
		wochenumsaetze[2] = 299.75;
		wochenumsaetze[3] = 0.0;
		wochenumsaetze[4] = 412.61;
		wochenumsaetze[5] = 664.53;
		wochenumsaetze[6] = 923.59;

	}

	public Parkhaus(int anzPlaetze) {
		plaetze = new Fahrzeug[anzPlaetze];
		umsatz = 0;
		wochenumsaetze = new double[55];
		Arrays.fill(wochenumsaetze, -1);
	}


	public int getNextPlace() {
		for (int i = 0; i < plaetze.length; i++) {
			if (plaetze[i] == null)
				return i;
		}
		return -1;
	}

	public void getWochenUmsaetze() {
		for (int i = 0; i < wochenumsaetze.length; i++) {
			if (wochenumsaetze[i] > 0.0)
				System.out.print("Woche " + (i + 1) + ": " + wochenumsaetze[i] + "€ \t | ");
		}
		System.out.println();
	}

	public boolean neueWoche() {
		for (int i = 0; i < wochenumsaetze.length; i++) {
			if (wochenumsaetze[i] == -1)
				wochenumsaetze[i] = umsatz;
			umsatz = 0;
			return true;
		}
		return false;
	}

	public boolean park(Fahrzeug f) {
		if (f.parked) {
			return false;
		} else {
			int platz = getNextPlace();
			if (platz >= 0) {
				plaetze[platz] = f;
				f.park(platz);
				return true;
			}
			return false;
		}
	}

	public boolean parkOnPlace(Fahrzeug f, int wunschplatz) {
		if (f.parked) {
			System.out.println("Fahrzeug " + f.kfz + " ist bereits geparkt!");
			return false;
		}
		if (wunschplatz < 0 || wunschplatz >= plaetze.length) {
			System.out.println("Bitte versuchen sie es erneut mit einer Nummer zwischen 0 und " + plaetze.length + "!");
			return false;
		}
		if (plaetze[wunschplatz] == null) {
			plaetze[wunschplatz] = f;
			f.park(wunschplatz);
			System.out.println("Fahrzeug " + f.kfz + " wurde auf Platz " + wunschplatz + " geparkt!");
			return true;
		}
		System.out.println("Parkplatz " + wunschplatz + " ist zur Zeit nicht verfügbar!");
		return false;
	}

	public boolean unpark(String k) throws Exception {
		for (Fahrzeug f : plaetze) {
			if (f != null && f.kfz.compareTo(k) == 0) {
				return unpark(f);
			}
		}

		throw new Exception("Kein Fahrzeug mit dem KFZ " + k + " vorhanden!");
	}

	public boolean unpark(Fahrzeug f) throws Exception {
		if (f.parked) {
			int parknumber = f.parkNR;
			f.unpark();

			double bill = 100 * (f.duration * pricePerSek);
			bill = Math.round(bill);
			bill = bill / 100;
			umsatz += bill;
			double dur = 100 * f.duration;
			dur = Math.round(dur);
			dur = dur / 100;
			int durat = (int) dur;
			addBill(f.kfz, bill, durat);
			plaetze[parknumber] = null;
			return true;
		} else {
			throw new Exception("Fahrzeug " + f.kfz + " ist nicht geparkt!");
		}
	}

	public boolean unpark(int place) throws Exception {
		if (place >= 0 && place < plaetze.length && plaetze[place] != null) {
			return unpark(plaetze[place]);
		} else {
			throw new Exception("Ungültige Platznummer!");
		}
	}

	public int freePlaces() {
		int anz = 0;
		for (Fahrzeug f : plaetze) {
			if (f == null) {
				anz++;
			}
		}
		return anz;
	}

	public void createMorePlaces(int anz) {
		Fahrzeug[] tmp = new Fahrzeug[plaetze.length + anz];
		System.arraycopy(plaetze, 0, tmp, 0, plaetze.length);
		plaetze = tmp;
	}

	public void showPlaces() {
		for (Fahrzeug f : plaetze) {
			if (f != null) {
				System.out.print(f.kfz + " > " + f.parkNR + " | ");
			}
		}
		System.out.println();
	}
}
