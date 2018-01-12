package parkhausprototype;
import java.util.Scanner;

public class ParkhausRun {

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		
		Parkhaus p = new Parkhaus(50);
		Fahrzeug f1 = new Fahrzeug("F1");
		Fahrzeug f2 = new Fahrzeug("F2");
		Fahrzeug f3 = new Fahrzeug("F3");
		Fahrzeug f4 = new Fahrzeug("F4");
		Fahrzeug f5 = new Fahrzeug("F5");
		
		System.out.println("Eingabe:");
		System.out.println("exit um die Simulation zu verlassen");
		System.out.println("showbill um den Rechnungsverlauf zu sehen");
		System.out.println("park um ein neues Fahrzeug zu parken");
		System.out.println("number unpark um einen bestimmten Platz frei zu machen");
		System.out.println("unpark um ein Fahrzeug raus zu fahren");
		
		boolean exit = false;
		while(exit == false){
			String eingabe = scan.next();
			switch(eingabe){
			case "exit":			exit = true; break;
			case "showbill": 		p.showBills();break;
			case "park": 			System.out.println("Neues KFZ:");
									String k = scan.next();
									p.park(new Fahrzeug(k));
									break;
			case "unpark":			System.out.println("KFZ:");
									String kf = scan.next();
									p.unpark(kf);
									break;
			case "number unpark":	System.out.println("Platz:");
			 						int pl = scan.nextInt();
									p.unpark(pl);
									break;
			
			}
		}
	}
}
