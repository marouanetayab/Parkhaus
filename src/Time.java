public class Time {
		private int hour, min;

		public Time(float time) {
			hour = (int) time;
			min = Math.round((time - hour) * 100);
		}
		private void fix(){
			if(min>=60){
				hour++;
				min-=60;
			}
			hour=hour % 24;
		}
		public int stunden() {
			return hour;
		}

		public int minuten() {
			return min;
		}

		void add(Time other) {
			min+=other.minuten();
			hour+=other.stunden();
			fix();
		}

		@Override
		public String toString() {
			String erg = "";
			if (hour <= 9) {
				erg += "0" + hour + ":";
			}else{
				erg+=hour+":";
			}
			if (min <= 9) {
				erg += "0" + min;
			}else{
				erg+=min;
			}
			return erg;
		}

		@Override
		public boolean equals(Object other) {
			if (other.getClass() != Time.class)
				return false;
			return((hour==((Time) other).stunden())&&(min==((Time) other).minuten()));
		}

		public float toFloat(){
			float x = stunden();
			x += (float)minuten()/100;
			return x;
		}
		
}

