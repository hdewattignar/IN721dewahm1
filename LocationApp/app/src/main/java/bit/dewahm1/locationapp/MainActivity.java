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
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int latitude;
    int longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button teleport = (Button)findViewById(R.id.btn_teleport);
        TeleportButtonHandler handler = new TeleportButtonHandler();
        teleport.setOnClickListener(handler);
    }

    public void generateLocation() {

        TextView longitudeText = (TextView)findViewById(R.id.txt_longitude);
        TextView latitudeText = (TextView)findViewById(R.id.txt_latitude);

        longitude = setLatorLong(90);
        latitude = setLatorLong(180);


        longitudeText.setText(Integer.toString(longitude));
        latitudeText.setText(Integer.toString(latitude));

    }

    public int setLatorLong(int max)
    {
        Random rnd = new Random();

        int value = rnd.nextInt(max - rnd.nextInt(max));

        return value;
    }


    public void setCity(String json)
    {
        try{
            JSONObject locationData = new JSONObject(json);
            String city;
            city = locationData.getString("geoplugin_place");
            TextView cityText = (TextView)findViewById(R.id.txt_closestCity);
            cityText.setText(city);
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

    }

    private class TeleportButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            generateLocation();
            makeConnection connect = new makeConnection();
            connect.execute();
        }
    }

    class makeConnection extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params) {

            String rawJSON = "";

            try{
                String urlString =  "http://www.geoplugin.net/extras/location.gp?lat=-45.8787605&long=170.5027976&format=json";

                URL URLObject = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                connection.connect();

                int responseCode = connection.getResponseCode();

                InputStream inputStream = connection.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String resposeString;

                StringBuilder stringBuilder = new StringBuilder();

                while((resposeString = bufferedReader.readLine()) != null)
                {
                    stringBuilder = stringBuilder.append(resposeString);
                }

                rawJSON = stringBuilder.toString();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            return rawJSON;

        }

        @Override
        protected void onPostExecute(String fetchedString)
        {
            setCity(fetchedString);
        }
    }
}
