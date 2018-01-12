package parkhausprototype;

public class Parkhaus implements ParkhausIF {

	Fahrzeug[] plaetze;
	String[][] bills;
	double pricepermin = 0.025; //entspricht 1.50€ prostunde

	public Parkhaus(int anzPlaetze) {
		plaetze = new Fahrzeug[anzPlaetze];
		billsreset();
	}

	private void billsreset() {
		bills = new String[3][50];
		bills[0][0] = "KFZ\t";
		bills[1][0] = "Bill";
		bills[2][0] = "Parktime";
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

		bills[0][i] = k;
		bills[1][i] = b;
		bills[2][i] = t;

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
			System.out.println("Fahrzeug " + f.kfz + " ist bereits geparkt!");
			return false;
		} else {
			int platz = getNextPlace();
			if (platz >= 0) {
				plaetze[platz] = f;
				f.park(platz);
				System.out.println("Fahrzeug "+f.kfz+" wurde auf Platz "+platz+" geparkt!");
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
		} else {
			if (plaetze[wunschplatz] == null) {
				plaetze[wunschplatz] = f;
				f.park(wunschplatz);
				System.out.println("Fahrzeug "+f.kfz+" wurde auf Platz "+wunschplatz+" geparkt!");
				return true;
			}
			System.out.println("Parkplatz " + wunschplatz + " ist zur Zeit nicht verfügbar!");
			return false;
		}
	}

	public boolean unpark(String k){
		for(Fahrzeug f: plaetze){
			if(f!=null && f.kfz.compareTo(k)==0){
				return unpark(f);
				
			}
		}
		System.out.println("Kein Fahrzeug mit dem KFZ "+k+" vorhanden!");
		return false;
	}
	public boolean unpark(Fahrzeug f) {
		if(f.parked){
			int parknumber = f.parkNR;
			f.unpark();
			String bill = ""+(f.duration*pricepermin);
			String dur = ""+ f.duration;
			addBill(f.kfz, bill, dur);
			plaetze[parknumber] = null;
			System.out.println("Fahrzeug "+f.kfz+" ist von Platz "+parknumber+" weggefahren!");
			return true;
		}
		System.out.println("Fahrzeug "+f.kfz+" ist nicht geparkt!");
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
				if(bills[i][j]!=null)
					System.out.print(" \t | " + bills[i][j]);
			}
			System.out.println();
		}
	}
}
