public interface Parkhaus {
	
	public int getNextPlace();				//sucht den n�chsten freien platz raus
  
	public void park(Fahrzeug auto);		//parkt das auto auf dem n�chsten freien Platz
    
	public void unPark(Fahrzeug auto);		//l�sst das auto raus, wenn rechnung beglichen ist
	
	public double payBill(Fahrzeug auto);	//begleicht die Rechnung f�r das jeweilige Auto

	public int freePlaces();				//anzahl der freien Pl�tze
	
	
}