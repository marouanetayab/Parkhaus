import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class JSON implements JSONInterface {
    private JsonObject root;

    JSON(String json) {
        JsonParser parser = new JsonParser();
        root = parser.parse(json).getAsJsonObject();
    }

    @Override
    public int sumValues() {
        // we know that the first level is always a JsonObject
        return sumObjValues(root);
    }

    private int sumObjValues(JsonObject obj) {
        int sum = 0;
        //For each key in Object, get the element and pass it to sumElValues, add the return value to the sum
        for (String key : obj.keySet()) {
            JsonElement el = obj.get(key);
            sum += sumElValues(el);
        }
        return sum;
    }

    private int sumArrValues(JsonArray arr) {
        int sum = 0;
        // For each element in the Array, pass the element to sumElValues, add the return value to the sum
        for (JsonElement el : arr) {
            sum += sumElValues(el);
        }
        return sum;
    }

    private int sumElValues(JsonElement el) {
        int sum = 0;
        // here we are a level under the previous and can check what kind of json type we got

        if (el.isJsonObject()) {
            // if element is JsonObject, pass the element to sumObjValues and add the return to the sum
            sum += sumObjValues(el.getAsJsonObject());
        } else if (el.isJsonArray()) {
            // if element is JsonArray, pass the element to sumArrValues and add the return to the sum
            sum += sumArrValues(el.getAsJsonArray());
        } else {
            // we know the element must be primitive (because Object and Arrays are checked before)
            // if the element (as primitive) is a number, add the value to the sum
            if (el.getAsJsonPrimitive().isNumber()) {
                sum += el.getAsInt();
            }
        }
        return sum;
    }

    @Override
    public String concatStrings() {
        // we know that the first level is always a JsonObject
        return concatObjValues(root);
    }

    private String concatObjValues(JsonObject obj) {
        String sum = "";
        //For each key in Object, get the element and pass it to concatElValues, concat the return value to the sum
        for (String key : obj.keySet()) {
            JsonElement el = obj.get(key);
            sum = sum.concat(concatElValues(el));
        }
        return sum;
    }

    private String concatArrValues(JsonArray arr) {
        String sum = "";
        for (JsonElement el : arr) {
            // For each element in the Array, pass the element to concatElValues, concat the return value to the sum
            sum = sum.concat(concatElValues(el));
        }
        return sum;
    }

    private String concatElValues(JsonElement el) {
        String sum = "";
        // here we are a level under the previous and can check what kind of json type we got
        if (el.isJsonObject()) {
            // if element is JsonObject, pass the element to concatObjValues and concat the return to the sum
            sum = sum.concat(concatObjValues(el.getAsJsonObject()));
        } else if (el.isJsonArray()) {
            // if element is JsonArray, pass the element to concatArrValues and concat the return to the sum
            sum = sum.concat(concatArrValues(el.getAsJsonArray()));
        } else {
            // we know the element must be primitive (because Object and Arrays are checked before)
            // if the element (as primitive) is not a number, concat the value to the sum
            if (!el.getAsJsonPrimitive().isNumber()) {
                sum = sum.concat(el.getAsString());
            }
        }
        return sum;
    }
}
