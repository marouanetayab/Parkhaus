package parkhausprototype;

public class Bill implements BillIF{
	private String fahrzeugName;
	private double kosten;
	private double zeit;

	Bill(String fahrzeugName, double kosten, double zeit) {
		this.fahrzeugName = fahrzeugName;
		this.kosten = kosten;
		this.zeit = zeit;
	}

	public String getFahrzeugName() {
		return fahrzeugName;
	}

	public void setFahrzeugName(String name) {
		this.fahrzeugName = name;
	}

	public double getKosten() {
		return kosten;
	}

	public void setKosten(double kosten) {
		this.kosten = kosten;
	}

	public double getZeit() {
		return zeit;
	}

	public void setZeit(double zeit) {
		this.zeit = zeit;
	}
}
