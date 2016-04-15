package bit.dewahm1.webservices_usersearch;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

    String userString;
    String JSONrawData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //wire up buttons
        Button btn = (Button)findViewById(R.id.btn_search);
        FillListButtonHandler btnHandler = new FillListButtonHandler();
        btn.setOnClickListener(btnHandler);


    }

    public String getUserInput() {
        //get the text from the editText
        EditText userinput = (EditText)findViewById(R.id.editText);
        userString = userinput.getText().toString();
        return userString;
    }

    public class FillListButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            getUserInput();

            makeConnection APITread = new makeConnection();
            APITread.execute(getUserInput());
        }
    }//END FillListButtonHandler

    class makeConnection extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... userString) {
            try{
                //set the url including the user input
                String urlString =  "http://ws.audioscrobbler.com/2.0/?" +
                        "method=artist.getSimilar&" +
                        "artist=" + userString + "&" +
                        "api_key=58384a2141a4b9737eacb9d0989b8a8c&" +
                        "limit=10&format=json";

                //create a url from the string
                URL URLObject = new URL(urlString);
                //create a connection
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                connection.connect();
                //record the response code. 200 means the connection is working
                int responseCode = connection.getResponseCode();
                //get the data from the connection and build a string
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

            //make JSON object
            JSONObject eventobj = eventData.getJSONObject("similarartists");

            //make an array
            JSONArray objectArray = eventobj.getJSONArray("artist");

            //get the length of the array
            int n = objectArray.length();

            //loop through the array getting the data required
            for(int i = 0;i < n; i++)
            {
                JSONObject currentobj = objectArray.getJSONObject(i);
                String eventName = currentobj.getString("name");
                output.add(eventName);
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }


        return output;
    }//END toArray

}
