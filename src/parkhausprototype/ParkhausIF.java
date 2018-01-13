package parkhausprototype;

import java.util.HashMap;

public interface ParkhausIF {
	double getUmsatz();

	void setUmsatz(double umsatz);

	double getPricePerSek();

	void setPricePerSek(double pricePerSek);

	HashMap<String, Bill> getBills();
	void addBill(String name, double kosten, double zeit); int getNextPlace();

	 boolean park(Fahrzeug auto);

	 boolean unpark(Fahrzeug auto)throws Exception;

	 int freePlaces();

	 boolean parkOnPlace(Fahrzeug f, int wunschplatz)throws Exception;

	 boolean unpark(String k)throws Exception;

	 boolean unpark(int place)throws Exception;

	 void createMorePlaces(int anz);


}
