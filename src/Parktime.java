import java.time.LocalTime;

/**
 * Parkhaus
 *
 * @author Johannes Heimbach
 * 29.10.17.
 */
class Parktime {
    int hours = 0;
    int minutes = 0;
    LocalTime begin,end,duration;
    
    public Parktime(){
        begin	= LocalTime.now();
        begin = begin.withNano(0);
        
    }
    
    int getHours(){
    	return hours;
    }
    int getMinutes(){
    	return minutes;
    }

    int getHoursRounded(){
    	if(minutes<=30){
    		return hours+1;
    	}
    	return hours;
    }
    
    int endAndGetDuration(){
    	end = LocalTime.now();
    	
    }
    int getDuration(){
    	duration = LocalTime.now().minus(begin);
    }
    int update(){
    	LocalTime
    }
}
