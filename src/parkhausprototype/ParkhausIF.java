package parkhausprototype;
public interface ParkhausIF {
	
	public int getNextPlace();				//sucht den naechsten freien platz raus
  
	public boolean park(Fahrzeug auto);		//parkt das auto auf dem naechsten freien Platz
    
	public boolean unpark(Fahrzeug auto);		//laesst das auto raus, wenn rechnung beglichen ist
	
	public int freePlaces();				//anzahl der freien Plaetze
	
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