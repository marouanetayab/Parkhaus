public interface ParkhausIF {
	
	public int getNextPlace();				//sucht den naechsten freien platz raus
  
	public void park(Fahrzeug auto);		//parkt das auto auf dem naechsten freien Platz
    
	public void unPark(Fahrzeug auto);		//laesst das auto raus, wenn rechnung beglichen ist
	
	public double payBill(Fahrzeug auto);	//begleicht die Rechnung fuer das jeweilige Auto

	public int freePlaces();				//anzahl der freien Plaetze
	
	
}