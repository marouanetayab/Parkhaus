
public class Parkhaus implements ParkhausIF{	
	int[] parkplaetze; 					
	
	public Parkhaus(int anzPlaetze){
		parkplaetze = new int[anzPlaetze];
		
		for(int i : parkplaetze){	
				i = 0;
			}
	}
	
	public int getNextPlace() {
		for(int i = 0; i < parkplaetze.length; i++){
			if(i!=0)
				return i;
			return -1;
		}	
	System.out.println("Parkhaus voll !");
	return 0;
	}
	public void park(Fahrzeug auto) {
		
	}

	public void unPark(Fahrzeug auto) {
		
	}

	public double payBill(Fahrzeug auto) {
	return 0;
	}

	public int freePlaces() {
		return 0;
	}

}
