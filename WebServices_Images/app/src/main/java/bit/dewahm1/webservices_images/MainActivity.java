package bit.dewahm1.webservices_images;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String JSONrawData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn_ShowImage);
        ButtonHandler handler = new ButtonHandler();
        btn.setOnClickListener(handler);
    }

    public class ButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            makeConnection connection = new makeConnection();
            connection.execute();
        }
    }

    class makeConnection extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... userString) {
            try{
                String urlString =  "http://ws.audioscrobbler.com/2.0/?" +
                        "method=chart.getTopArtists&" +
                        "api_key=58384a2141a4b9737eacb9d0989b8a8c&" +
                        "limit=1&format=json";

                URL URLObject = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                connection.connect();

                int responseCode = connection.getResponseCode();

                InputStream inputStream = connection.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String responseString;

                StringBuilder stringBuilder = new StringBuilder();

                while((responseString = bufferedReader.readLine()) != null)
                {
                    stringBuilder = stringBuilder.append(responseString);
                }

                JSONrawData = stringBuilder.toString();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            return JSONrawData;
        }

        @Override
        protected void onPostExecute(String fetchedString)
        {
            String JSONString = fetchedString;
            TextView artistText = (TextView)findViewById(R.id.textView);
            artistText.setText(JSONString);
        }
    }
}
