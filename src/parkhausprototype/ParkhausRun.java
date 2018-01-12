package parkhausprototype;
import java.util.Scanner;

public class ParkhausRun {

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		
		Parkhaus p = new Parkhaus(50);
		
		System.out.println("Eingabe:");
		System.out.println("exit \t\t um die Simulation zu verlassen");
		System.out.println("showbills \t um den Rechnungsverlauf zu sehen");
		System.out.println("showplaces \t um ein Überblick über die Parkplätze zu kriegen");
		System.out.println("park \t\t um ein neues Fahrzeug zu parken");
		System.out.println("numberPark \t um ein neues Fahrzeug auf einem bestimmten Platz zu parken");
		System.out.println("unpark \t\t um ein Fahrzeug raus zu fahren");
		System.out.println("numberUnpark \t um einen bestimmten Platz frei zu machen");
		
		boolean exit = false;
		while(exit == false){
			String eingabe = scan.next();
			switch(eingabe){
			case "exit":			
									exit = true; break;
			case "showbills": 		
									p.showBills();break;
			case "showplaces":
									p.showPlaces();break;
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
									p.parkOnPlace(new Fahrzeug(k2),p2);
									break;
			case "unpark":			
									System.out.println("KFZ:");
									String k3 = scan.next();
									p.unpark(k3);
									break;
			case "numberUnpark":	System.out.println("Platz:");
			 						int pl = scan.nextInt();
									p.unpark(pl);
									break;
			default:				System.out.println("Ungültige eingabe!");
				
			}
		}
	}
}
