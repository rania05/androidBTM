package Entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParserr {
    private HashMap<String,String> parseJsonObject (JSONObject object) throws JSONException {
   HashMap<String,String> dataList = new HashMap<>();
 try {

     String name = object.getString("name");
     //get latitude from object
             String latitude = object.getJSONObject("geometry").getJSONObject("location").getString("lat");
             //get longitude from obect
     String longitude = object.getJSONObject("geometry").getJSONObject("location").getString("lng");
     // put all value in hash map
     dataList.put("name",name);
     dataList.put("lat",latitude);
     dataList.put("lng",longitude);
 } catch (Exception e) {
     e.printStackTrace();
 }
  return dataList;
    }
    /*
    private List<HashMap<String,String>> parseJsonArray(JsonArray jsonArray)
    {
        List<HashMap<String,String>> dataList = new ArrayList<>();
        for (int i=0 ; i< jsonArray.length();i++)
        {
            HashMap<String,String> data = parseJsonObject((JsonObject)jsonArray.get(i));

        }
    }*/
}
