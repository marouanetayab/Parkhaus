package parkhausprototype;
public interface ParkhausIF {
	
	public int getNextPlace();				//sucht den naechsten freien platz raus
  
	public boolean park(Fahrzeug auto);		//parkt das auto auf dem naechsten freien Platz
    
	public boolean unpark(Fahrzeug auto);		//laesst das auto raus, wenn rechnung beglichen ist
	
	public int freePlaces();				//anzahl der freien Plaetze
	
	
}