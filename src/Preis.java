public interface Preis {
 
	public void setPricePerHour(double pricePerHour);		//setzt den Preis pro Stunde (ggf dynamisch?) 
    
	public double getPriceForTime(Time time);				//gibt den Preis für die Parkzeit
}