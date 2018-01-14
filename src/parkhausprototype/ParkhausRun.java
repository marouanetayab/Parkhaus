package parkhausprototype;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ParkhausRun {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Parkhaus p = new Parkhaus(5);
		System.out.println("Wilkommen zum Virtuosen Parkhaus:");
		System.out.println("Folgende Befehle stehen zur Verfuegung");
		printHelp();

		boolean exit = false;
		while (!exit) {
			String eingabe = scan.next();
			switch (eingabe) {
				case "help":
					printHelp();
					break;
				case "exit":
					exit = true;
					break;
				case "showbills":
					System.out.println("Rechnungen:");
					System.out.println(Utils.buildTableOutputBills(p.getBills().values()));
					break;
				case "showplaces":
					System.out.println(Utils.buildTableOutputPlaces(p.plaetze));
					System.out.println();
					break;
				case "park":
					System.out.println("Neues KFZ:");
					String k1 = scan.next();
					Fahrzeug fahrzeug = new Fahrzeug(k1);
					boolean isParked = p.park(fahrzeug);
					if (!isParked) {
						if (fahrzeug.parked) {
							System.out.println("Fahrzeug " + fahrzeug.kfz + " ist bereits geparkt!");
						} else {
							System.out.println("Parkhaus ist voll!");
						}
					} else {
						System.out.println("Fahrzeug " + fahrzeug.kfz + " wurde auf Platz " + fahrzeug.parkNR + " geparkt!");
					}
					break;
				case "numberPark":
					System.out.println("Neues KFZ:");
					String k2 = scan.next();
					System.out.println("Platz:");
					int p2 = scan.nextInt();
					Fahrzeug fz = new Fahrzeug(k2);
					try {
						boolean parked = p.parkOnPlace(fz, p2);
						if (parked) {
							System.out.println("Fahrzeug " + fz.kfz + " wurde auf Platz " + fz.parkNR + " geparkt!");
						} else {
							if (!fz.parked) {
								System.out.println("Parkplatz " + p2 + " ist zur Zeit nicht verfügbar!");
							} else {
								System.out.println("Fahrzeug " + fz.kfz + " ist bereits geparkt!");
							}
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case "unpark":
					System.out.println("KFZ:");
					String k3 = scan.next();
					try {
						p.unpark(k3);
						System.out.println("Fahrzeug " + k3 + " weggefahren!");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case "numberUnpark":
					System.out.println("Platz:");
					int pl = scan.nextInt();
					try {
						p.unpark(pl);
						System.out.println("Fahrzeug von Platz " + pl + " weggefahren!");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case "expand":
					System.out.println("Anzahl der neuen Plätze");
					int a1 = scan.nextInt();
					p.createMorePlaces(a1);
					System.out.println("Es gibt nun " + p.plaetze.length + " Plätze im Parkhaus!");
					break;
				case "free":
					int anz = p.freePlaces();
					System.out.println("Es sind noch " + anz + " Plätze frei!");
					break;
				case "sales":
					System.out.println("Der bisherige Umsatz beträgt " + Utils.formatMoney(p.getUmsatz())+ "€");
					break;
				case "pps":
					System.out.println("Neuer Betrag:");
					double p3 = scan.nextDouble();
					p.setPricePerSek(p3);
					System.out.println("Der Preis pro Sekunde beträgt " + p3 + "€");
					break;
				case "allsales":
					//p.getWochenUmsaetze();
					break;
				case "sleep":
					try {
						TimeUnit.SECONDS.sleep(scan.nextInt());
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					System.out.println("Ungültige eingabe!");

			}
		}
		scan.close();
	}

	private static void printHelp() {
		System.out.println("help \t\t um diese Nachricht zu sehen");
		System.out.println("exit \t\t um die Simulation zu verlassen");
		System.out.println("showbills \t um den Rechnungsverlauf zu sehen");
		System.out.println("showplaces \t um ein �berblick �ber die Parkpl�tze zu kriegen");
		System.out.println("park \t\t um ein neues Fahrzeug zu parken");
		System.out.println("numberPark \t um ein neues Fahrzeug auf einem bestimmten Platz zu parken");
		System.out.println("unpark \t\t um ein Fahrzeug raus zu fahren");
		System.out.println("numberUnpark \t um einen bestimmten Platz frei zu machen");
		System.out.println("expand \t\t um mehr Parkplaetze zu erschaffen");
		System.out.println("free \t\t um die Anzahl freier Pl�tze zu erhalten");
		System.out.println("sales \t\t um den bisherigen Umsatz zu sehen");
		System.out.println("allsales \t um alle Ums�tze zu sehen");
		System.out.println("pps \t\t um die Parkgeb�hren pro Sekunde anzupassen");
	}
}
