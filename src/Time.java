public class Time implements Parktime {
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
		public int getHours() {
			return hour;
		}

		public int getMinutes() {
			return min;
		}

		void add(Time other) {
			min+=other.getHours();
			hour+=other.getMinutes();
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
			return((hour==((Time) other).getHours())&&(min==((Time) other).getMinutes()));
		}

		public float toFloat(){
			float x = getHours();
			x += (float)getMinutes()/100;
			return x;
		}

	@Override
	public int getHoursRounded() {
		return (int)Math.floor(toFloat());
	}
}

