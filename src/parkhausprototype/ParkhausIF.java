package parkhausprototype;

import java.util.HashMap;

public interface ParkhausIF {
	double getUmsatz();

	void setUmsatz(double umsatz);

	double getPricePerSek();

	void setPricePerSek(double pricePerSek);

	HashMap<String, Bill> getBills();

	void addBill(String name, double kosten, double zeit);

	int getNextPlace();                //sucht den naechsten freien platz raus

	boolean park(Fahrzeug auto);        //parkt das auto auf dem naechsten freien Platz

	boolean unpark(Fahrzeug auto) throws Exception;        //laesst das auto raus, wenn rechnung beglichen ist

	int freePlaces();                //anzahl der freien Plaetze

	boolean parkOnPlace(Fahrzeug f, int wunschplatz) throws Exception;

	boolean unpark(String k) throws Exception;

	boolean unpark(int place) throws Exception;

	void createMorePlaces(int anz);
}
