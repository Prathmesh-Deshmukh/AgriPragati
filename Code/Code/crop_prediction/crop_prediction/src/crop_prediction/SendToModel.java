package crop_prediction;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import org.json.JSONObject;


public class SendToModel {
    static String N ;
    static String P ;
    static String K ;
    static String temperature ;
    static String humidity ;
    static String ph;
    static String rainfall;
    static String prediction;
    static String fertilizer_prediction;
    static String entry_id;
    static String url;

    public static void main(String[] args) throws Exception {
        // create a JSON object with the values you want to send

    }

    public void predict() throws Exception{
        url = "https://be3a-2404-ba00-f901-19f7-c7be-e3eb-f00-cec3.ngrok-free.app/";

    	
        Random rand = new Random();
        ThingSpeakRetriever obj = new ThingSpeakRetriever();
        String[] vals = obj.fetch();

        SendToModel.N = vals[0];
        SendToModel.P = vals[1];
        SendToModel.K = vals[2];
        SendToModel.temperature = vals[3];
        SendToModel.humidity = vals[4];
        SendToModel.ph= vals[5];
        SendToModel.rainfall= vals[6];
        SendToModel.entry_id = vals[7];

//        N = String.valueOf(rand.nextInt(100));
//        P = String.valueOf(rand.nextInt(100));	
//        K = String.valueOf(rand.nextInt(100));
//        temperature = vals[3];
//        humidity = vals[4];
//        ph= String.valueOf(6);
//        rainfall= vals[6];


//        for(int l=0;l<7;l++)
//            System.out.println(vals[l]);

        System.out.println("Nitrogen: " + N);
        System.out.println("Phosphorous: " + P);
        System.out.println("Potassium: " + K);
        System.out.println("Temperature: " + temperature +"°C");
        System.out.println("Humidity: " + humidity+"%");
        System.out.println("PH: " + ph);
        System.out.println("Rainfall: " + rainfall);

        JSONObject json = new JSONObject();
        json.put("N", N);
        json.put("P", P);
        json.put("K", K);
        json.put("temperature", temperature);
        json.put("humidity", humidity);
        json.put("ph", ph);
        json.put("rainfall", rainfall);

        // convert the JSON object to a byte array
        byte[] postData = json.toString().getBytes("UTF-8");

        // set up the HTTP connection
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // send the request body
        OutputStream os = conn.getOutputStream();
        os.write(postData,0,postData.length);
        os.flush();
        os.close();

        // read the response from the server
        InputStream i = conn.getInputStream();   
        
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
          (i, StandardCharsets.UTF_8))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        
        String responseBody = textBuilder.toString();
        
        
        JSONObject responseBodyJSON = new JSONObject(responseBody);

        prediction = responseBodyJSON.getString("prediction");
        prediction = prediction.substring(2,prediction.length() - 2);
        
        fertilizer_prediction = responseBodyJSON.getString("fertilizer");
        fertilizer_prediction = fertilizer_prediction.substring(2,fertilizer_prediction .length() - 2);

        conn.disconnect();

    }


}
