package crop_prediction;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ThingSpeakRetriever {
    public static void main(String[] args) throws Exception {
        ThingSpeakRetriever obj = new ThingSpeakRetriever();
        String[] ans = obj.fetch();
        for(int i=0;i<7;i++)
            System.out.println(ans[i]);
    }

    public String[] fetch() throws Exception {
        // ThingSpeak channel ID
        String channelID = "2353739";

        // ThingSpeak read API key
        String apiKey = "1WJ29OUCD872DETL";
        // create a new HTTP connection to retrieve the most recent value
        URL url = new URL("https://api.thingspeak.com/channels/" + channelID + "/feeds/last.json?api_key=" + apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = reader.readLine();
        // parse the response to retrieve the most recent value
        System.out.println(response);

        JSONObject jsonObject = new JSONObject(response);

        int field1 = jsonObject.getInt("field1");
        int field2 = jsonObject.getInt("field2");
        int field3 = jsonObject.getInt("field3");
        
        if(field1>150)
        	field1 = 0;
        if(field2>150)
        	field2 = 0;
        if(field3>150)
        	field3 = 0;
        double field4 = jsonObject.getDouble("field4");
        double field5 = jsonObject.getDouble("field5");
        double field6 = jsonObject.getDouble("field6");
//        double field7 = jsonObject.getDouble("field7");
        double field7 = 72.0;
        int field8 = jsonObject.getInt("entry_id");


        String[] vals = new String[8];
        vals[0] = String.valueOf(field1);
        vals[1] = String.valueOf(field2);
        vals[2] = String.valueOf(field3);
        vals[3] = String.valueOf(field4);
        vals[4] = String.valueOf(field5);
        vals[5] = String.valueOf(field6);
        vals[6] = String.valueOf(field7);
        vals[7] = String.valueOf(field8);




        return vals;
    }
}


