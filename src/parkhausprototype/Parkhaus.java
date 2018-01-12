package parkhausprototype;

public class Parkhaus implements ParkhausIF {

	Fahrzeug[] plaetze;
	String[][] bills;
	double[] wochenumsaetze;
	double umsatz;
	double pricepersek = 0.025;

	public void dummy_daten_umsaetze(){
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
		billsreset();
		umsatz = 0;
		wochenumsaetze = new double[55];
		for(double data : wochenumsaetze){
			data = -1;
		}
	}

	
	private void billsreset() {
		bills = new String[3][50];
		bills[0][0] = "KFZ\t\t";
		bills[1][0] = "Kosten in €\t";
		bills[2][0] = "Parkzeit in Sek";
	}

	private void addBill(String k, String b, String t) {
		int i = 1;
		for (i = 1; i < bills[0].length; i++) {
			if (bills[0][i] == null)
				break;
		}
		if (bills[0][i] != null) {
			longerBills(50);
			i++;
		}

		bills[0][i] = k + "\t";
		bills[1][i] = b + "\t";
		bills[2][i] = t + "\t";

	}

	
	public int getNextPlace() {
		for (int i = 0; i < plaetze.length; i++) {
			if (plaetze[i] == null)
				return i;
		}
		return -1;
	}

	public void pps(double neu) {
		pricepersek = neu;
		System.out.println("Der Preis pro Sekunde beträgt " + pricepersek + "€");
	}

	public double getUmsatz() {
		System.out.println("Der bisherige Umsatz beträgt " + umsatz + "€");
		return umsatz;
	}
	
	public void getWochenUmsaetze(){
		for( int i = 0; i < wochenumsaetze.length;i++){
			if(wochenumsaetze[i]>0.0)
				System.out.print("Woche "+ (i+1)+": "+wochenumsaetze[i]+"€ \t | ");
		}
		System.out.println();
	}
	
	public boolean neueWoche(){
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
			System.out.println("Fahrzeug " + f.kfz + " ist bereits geparkt!");
			return false;
		} else {
			int platz = getNextPlace();
			if (platz >= 0) {
				plaetze[platz] = f;
				f.park(platz);
				System.out.println("Fahrzeug " + f.kfz + " wurde auf Platz " + platz + " geparkt!");
				return true;
			}
			System.out.println("Parkhaus ist voll!");
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

	public boolean unpark(String k) {
		for (Fahrzeug f : plaetze) {
			if (f != null && f.kfz.compareTo(k) == 0) {
				return unpark(f);

			}
		}
		System.out.println("Kein Fahrzeug mit dem KFZ " + k + " vorhanden!");
		return false;
	}

	public boolean unpark(Fahrzeug f) {
		if (f.parked) {
			int parknumber = f.parkNR;
			f.unpark();

			double bill = 100 * (f.duration * pricepersek);
			bill = Math.round(bill);
			bill = bill / 100;
			umsatz += bill;
			double dur = 100 * f.duration;
			dur = Math.round(dur);
			dur = dur / 100;
			int durat = (int)dur;
			addBill(f.kfz, "" + bill, "" + durat);
			plaetze[parknumber] = null;
			System.out.println("Fahrzeug " + f.kfz + " ist von Platz " + parknumber + " weggefahren!");
			return true;
		}
		System.out.println("Fahrzeug " + f.kfz + " ist nicht geparkt!");
		return false;
	}

	public boolean unpark(int place) {
		if (place >= 0 && place < plaetze.length && plaetze[place] != null)
			return unpark(plaetze[place]);
		else
			System.out.println("Ungültige Platznummer!");
		return false;
	}

	public int freePlaces() {
		int anz = 0;
		for (Fahrzeug f : plaetze) {
			if (f == null) {
				anz++;
			}
		}
		System.out.println("Es sind noch " + anz + " Plätze frei!");
		return anz;
	}

	public void createMorePlaces(int anz) {
		Fahrzeug[] tmp = new Fahrzeug[plaetze.length + anz];
		for (int i = 0; i < plaetze.length; i++) {
			tmp[i] = plaetze[i];
		}
		plaetze = tmp;
	}

	private void longerBills(int anz) {
		String[][] tmp = new String[3][bills.length + anz];
		for (int i = 0; i < bills.length; i++) {
			for (int j = 0; j < bills[0].length; j++) {
				tmp[i][j] = bills[i][j];
			}
			bills = tmp;
		}
	}

	public void showBills() {
		for (int i = 0; i < bills.length; i++) {
			for (int j = 0; j < bills[0].length; j++) {
				if (bills[i][j] != null)
					System.out.print(" \t | " + bills[i][j]);
			}
			System.out.println();
		}
	}

	public void showPlaces() {
		for (int i = 0; i < plaetze.length; i++) {
			Fahrzeug f = plaetze[i];
			if (f != null) {
				System.out.print(f.kfz + " > " + f.parkNR + " | ");
			}
		}
		System.out.println();
	}
}
