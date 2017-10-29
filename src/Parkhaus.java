public interface Parkhaus {
	
	public int getNextPlace();				//sucht den nächsten freien platz raus
  
	public void park(Fahrzeug auto);		//parkt das auto auf dem nächsten freien Platz
    
	public void unPark(Fahrzeug auto);		//lässt das auto raus, wenn rechnung beglichen ist
	
	public double payBill(Fahrzeug auto);	//begleicht die Rechnung für das jeweilige Auto

	public int freePlaces();				//anzahl der freien Plätze
	
	
}