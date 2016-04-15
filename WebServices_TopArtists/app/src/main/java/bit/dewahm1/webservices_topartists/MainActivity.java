package bit.dewahm1.webservices_topartists;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String JSONrawData;
    //String JSONinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn_fillList);
        FillListButtonHandler btnHandler = new FillListButtonHandler();
        btn.setOnClickListener(btnHandler);

    }

    public class FillListButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            makeConnection APITread = new makeConnection();
            APITread.execute();
        }
    }//END FillListButtonHandler

    class makeConnection extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params) {
            try{
                String urlString =  "http://ws.audioscrobbler.com/2.0/?" +
                        "method=chart.getTopArtists&" +
                        "api_key=58384a2141a4b9737eacb9d0989b8a8c&" +
                        "limit=10&format=json";

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
            ArrayList<String> artistArray = makeArray(JSONString);
            populateList(artistArray);

        }
    }

    private void populateList(ArrayList<String> artistList) {

        //populate the list
        ListView events = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, artistList);
        events.setAdapter(adapter);
    }//End populateList

    public ArrayList<String> makeArray(String JSONinput) {

        ArrayList<String> output = new ArrayList<String>();

        try
        {
            //make JSON object from JSONinput
            JSONObject eventData = new JSONObject(JSONinput);

            //make JSON object with a key from eventData
            JSONObject eventobj = eventData.getJSONObject("artists");

            //make an array of 'event' from eventobj
            JSONArray objectArray = eventobj.getJSONArray("artist");

            //get the length of the array
            int n = objectArray.length();

            for(int i = 0;i < n; i++)
            {
                //get the 'title' from each object in the array and add it to output
                JSONObject currentobj = objectArray.getJSONObject(i);
                String eventName = currentobj.getString("name") + "  "+ currentobj.getString("listeners");
                output.add(eventName);
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        //return the array of 'title' strings
        return output;
    }//END toArray

}
