package parkhausprototype;

import java.time.LocalDateTime;

public class Fahrzeug implements FahrzeugIF {

	String kfz;
	int parkNR;
	boolean parked;
	LocalDateTime begin, end;
	double duration;

	public Fahrzeug(String k) {
		kfz = k;
		reset();
	}

	public Fahrzeug(String kfz, int parkNR, LocalDateTime begin) {
		this.kfz = kfz;
		this.parkNR = parkNR;
		this.begin = begin;
		this.parked = true;
	}

	public void reset() {
		parkNR = -1;
		parked = false;
	}

	public void park(int nr) {
		parked = true;
		parkNR = nr;
		begin = LocalDateTime.now();
		begin = begin.withNano(0);
	}

	public void unpark() {
		parked = false;
		parkNR = -1;
		end = LocalDateTime.now();
		end = end.withNano(0);
		duration = dura();
	}

	private double dura() {
		double eh = end.getHour();
		double em = end.getMinute();
		double es = end.getSecond();
		double bh = begin.getHour();
		double bm = begin.getMinute();
		double bs = begin.getSecond();

		double endinmin = 1 + (eh * 60) + 60*em + es;
		double begininmin = 1 + (bh * 60) + 60*bm + bs;
		return endinmin - begininmin;
	}
}
