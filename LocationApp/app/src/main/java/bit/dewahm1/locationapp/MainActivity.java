package bit.dewahm1.locationapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button teleport = (Button)findViewById(R.id.btn_teleport);
        TeleportButtonHandler handler = new TeleportButtonHandler();
        teleport.setOnClickListener(handler);
    }

    public void generateLocation() {

        longitude = setLatorLong(90);
        latitude = setLatorLong(180);
    }

    public void setDisplay()
    {
        TextView longitudeText = (TextView)findViewById(R.id.txt_longitude);
        TextView latitudeText = (TextView)findViewById(R.id.txt_latitude);

        longitudeText.setText(Double.toString(longitude));
        latitudeText.setText(Double.toString(latitude));
    }

    public double setLatorLong(int max)
    {
        Random rnd = new Random();

        double value = rnd.nextInt(max - rnd.nextInt(max));

        return value;
    }


    public String setCity(String json)
    {
        String city = null;
        try{
            JSONObject locationData = new JSONObject(json);
            city = locationData.getString("geoplugin_place");
            TextView cityText = (TextView)findViewById(R.id.txt_closestCity);
            cityText.setText(city);
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return city;
    }

    private class TeleportButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            getCityAsync APIThread = new getCityAsync();
            APIThread.execute();
        }
    }

    class getCityAsync extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params) {
            String JSON = null;

            while(getCity(JSON) == null) {

                generateLocation();

                String urlString = "http://www.geoplugin.net/extras/location.gp?" +
                        "lat=" + latitude +
                        "&long=" + longitude +
                        "&format=json";

                JSON = getJSONfromURL(urlString);
            }
            return JSON;
        }

        @Override
        protected void onPostExecute(String fetchedString)
        {
            setDisplay();
            setCity(fetchedString);
        }
    }

    public String getCity(String json)
    {
        String city = null;

        try{
            if(json == null)
                city = null;
            else
            {
                JSONObject cityObj = new JSONObject(json);

                city = cityObj.optString("geoplugin_place");
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
            city = null;
        }

        return city;
    }

    public String getJSONfromURL(String url)
    {
        String json = null;

        try{
            URL URLobject = new URL(url); //can throw malformed exception

            HttpURLConnection connection = (HttpURLConnection)URLobject.openConnection(); //can throw IO exception

            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String responseString;
            StringBuilder stringBuilder = new StringBuilder();

            while((responseString = bufferedReader.readLine()) != null)
            {
                stringBuilder = stringBuilder.append(responseString);
            }

            json = stringBuilder.toString();

            if(json.equals("[[]]"))
            {
                return null;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return  json;
    }
}
