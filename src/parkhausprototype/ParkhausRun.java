package parkhausprototype;

import java.util.Scanner;

public class ParkhausRun {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Parkhaus p = new Parkhaus(5);
		p.dummy_daten_umsaetze();
		System.out.println("Eingabe:");
		System.out.println("exit \t\t um die Simulation zu verlassen");
		System.out.println("showbills \t um den Rechnungsverlauf zu sehen");
		System.out.println("showplaces \t um ein Überblick über die Parkplätze zu kriegen");
		System.out.println("park \t\t um ein neues Fahrzeug zu parken");
		System.out.println("numberPark \t um ein neues Fahrzeug auf einem bestimmten Platz zu parken");
		System.out.println("unpark \t\t um ein Fahrzeug raus zu fahren");
		System.out.println("numberUnpark \t um einen bestimmten Platz frei zu machen");
		System.out.println("expand \t\t um mehr Parkplaetze zu erschaffen");
		System.out.println("free \t\t um die Anzahl freier Plätze zu erhalten");
		System.out.println("sales \t\t um den bisherigen Umsatz zu sehen");
		System.out.println("allsales \t um alle Umsätze zu sehen");
		System.out.println("pps \t\t um die Parkgebühren pro Sekunde anzupassen");

		boolean exit = false;
		while (exit == false) {
			String eingabe = scan.next();
			switch (eingabe) {
			case "exit":
				exit = true;
				break;
			case "showbills":
				p.showBills();
				break;
			case "showplaces":
				p.showPlaces();
				break;
			case "park":
				System.out.println("Neues KFZ:");
				String k1 = scan.next();
				p.park(new Fahrzeug(k1));
				break;
			case "numberPark":
				System.out.println("Neues KFZ:");
				String k2 = scan.next();
				System.out.println("Platz: ");
				int p2 = scan.nextInt();
				p.parkOnPlace(new Fahrzeug(k2), p2);
				break;
			case "unpark":
				System.out.println("KFZ:");
				String k3 = scan.next();
				p.unpark(k3);
				break;
			case "numberUnpark":
				System.out.println("Platz:");
				int pl = scan.nextInt();
				p.unpark(pl);
				break;
			case "expand":
				System.out.println("Anzahl der neuen Plätze");
				int a1 = scan.nextInt();
				p.createMorePlaces(a1);
				System.out.println("Es gibt nun " + p.plaetze.length + " Plätze im Parkhaus!");
				break;
			case "free":
				p.freePlaces();
				break;
			case "sales":
				p.getUmsatz();
				break;
			case "pps":
				System.out.println("Neuer Betrag:");
				double p3 = scan.nextDouble();
				p.pps(p3);
				break;
			case "allsales":
				p.getWochenUmsaetze();
				break;
			default:
				System.out.println("Ungültige eingabe!");

			}
		}
		scan.close();
	}
}
