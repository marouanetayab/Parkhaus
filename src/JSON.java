import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

class JSON implements JSONInterface {
	JSONObject object = new JSONObject();
	JSONParser parser = new JSONParser();
	Object obj = null;
	
    JSON(String json) {
    	 try {
			obj = parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 object = (JSONObject) obj;
    }

    @Override
    public int sumValues() {
    	int sum = 0;
    	for(int i =0; i < object.size(); i++)
    	{
    		int aux = (int) object.get(i);
    		sum += aux;
    	}
        return sum;
    }

    @Override
    public String concatStrings() {
    	String str = "";
    	for(int i =0; i<object.size(); i++){
    		str += (String) object.get(i);
    	}
        return str;
    }
    

}
