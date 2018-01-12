package parkhausprototype;

import java.time.LocalTime;

public class Fahrzeug implements FahrzeugIF {

	String kfz;
	int parkNR;
	boolean parked;
	LocalTime begin, end;
	long duration;

	public Fahrzeug(String k) {
		kfz = k;
		reset();
	}

	public void reset() {
		parkNR = -1;
		parked = false;
	}

	public void park(int nr) {
		parked = true;
		parkNR = nr;
		begin = LocalTime.now();
		begin = begin.withNano(0);
	}

	public void unpark() {
		parked = false;
		parkNR = -1;
		end = LocalTime.now();
		end = end.withNano(0);
		duration = dura();
	}

	private long dura() {
		long eh = end.getHour();
		long em = end.getMinute();
		long es = end.getSecond();
		long bh = begin.getHour();
		long bm = begin.getMinute();
		long bs = begin.getSecond();

		long endinmin = 1 + (eh * 60) + em + (es / 60);
		long begininmin = 1 + (bh * 60) + bm + (bs / 60);
		return endinmin - begininmin;
	}
}
