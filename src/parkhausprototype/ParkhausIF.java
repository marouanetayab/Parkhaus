package parkhausprototype;

public interface ParkhausIF {
	
	public int getNextPlace();				
	
	public boolean park(Fahrzeug auto);	
	
	public boolean unpark(Fahrzeug auto);		
	
	public int freePlaces();				
	
	public void pps(double neu);
	
	public double getUmsatz();
	
	public void getWochenUmsaetze();
	
	public boolean neueWoche();
	
	public boolean parkOnPlace(Fahrzeug f, int wunschplatz);
	
	public boolean unpark(String k);
	
	public boolean unpark(int place);
	
	public void createMorePlaces(int anz);
	
	public void showBills();
	
	public void showPlaces();
}
