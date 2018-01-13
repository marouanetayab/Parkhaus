package parkhausprototype;

import java.util.HashMap;

public class Parkhaus implements ParkhausIF {

	Fahrzeug[] plaetze;
	private HashMap<String, Bill> bills = new HashMap<>();
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


	public Parkhaus(int anzPlaetze) {
		plaetze = new Fahrzeug[anzPlaetze];
		umsatz = 0;
	}


	public int getNextPlace() {
		for (int i = 0; i < plaetze.length; i++) {
			if (plaetze[i] == null)
				return i;
		}
		return -1;
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

	public boolean parkOnPlace(Fahrzeug f, int wunschplatz) throws Exception {
		if (f.parked) {
			return false;
		}
		if (wunschplatz < 0 || wunschplatz >= plaetze.length) {
			throw new Exception("Bitte versuchen sie es erneut mit einer Nummer zwischen 0 und " + (plaetze.length - 1) + "!");
		}
		if (plaetze[wunschplatz] == null) {
			plaetze[wunschplatz] = f;
			f.park(wunschplatz);
			return true;
		}
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

			double bill = Utils.round(f.duration * pricePerSek, 2);
			umsatz += bill;
			addBill(f.kfz, bill, Utils.round(f.duration, 2));
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
}
