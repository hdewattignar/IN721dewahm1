package bit.dewahm1.formatedfileio;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //JSON file
    public static final String JSONDunedinEvents = "dunedin_events.json";
    String JSONinput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //wire up the button
        Button btnFillList = (Button)findViewById(R.id.btn_fillList);
        FillListButtonHandler handler = new FillListButtonHandler();
        btnFillList.setOnClickListener(handler);

        //wire up the listview
        ListView lvDunedinEvents = (ListView)findViewById(R.id.listView_dunedinEventsList);
        onListViewClickHandler listHandler = new onListViewClickHandler();
        lvDunedinEvents.setOnItemClickListener(listHandler);
    }

    public class FillListButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            //get the data from the JSON file
            JSONinput = makeJSONString(JSONDunedinEvents);

            //make an arrayList from the data
            ArrayList<String> dunedinEventList = toArray(JSONinput);

            //use the array to populate the list
            populateList(dunedinEventList);
        }
    }//END FillListButtonHandler

    private void populateList(ArrayList<String> dunedinEventList) {

        //populate the list with the dunedinEventList array
        ListView events = (ListView)findViewById(R.id.listView_dunedinEventsList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, dunedinEventList);
        events.setAdapter(adapter);
    }//End populateList

    public ArrayList<String> toArray(String JSONinput) {

        ArrayList<String> output = new ArrayList<String>();

        try
        {
            //make JSON object from JSONinput
            JSONObject eventData = new JSONObject(JSONinput);

            //make JSON object with a key from eventData
            JSONObject eventobj = eventData.getJSONObject("events");

            //make an array of 'event' from eventobj
            JSONArray objectArray = eventobj.getJSONArray("event");

            //get the length of the array
            int n = objectArray.length();

            for(int i = 0;i < n; i++)
            {
                //get the 'title' from each object in the array and add it to output
                JSONObject currentobj = objectArray.getJSONObject(i);
                String eventName = currentobj.getString("title");
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

    public String makeJSONString(String file)
    {
        String input = "";
        AssetManager am = getAssets();

        try
        {
            //make an input stream to read the file
            InputStream is = am.open(file);

            //get the number of bytes that are being read in
            int fileSizeInBytes = is.available();
            byte[] JSONbuffer = new byte[fileSizeInBytes];

            //read the stream and close the file
            is.read(JSONbuffer);
            is.close();

            //save the string
            input = new String(JSONbuffer);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return input;
    }//END makeJSONString

    public void eventToast(String JSONInput, int position){

        ListView eventList = (ListView)findViewById(R.id.listView_dunedinEventsList);

        try {
            JSONObject eventData = new JSONObject(JSONInput);
            JSONObject eventObject = eventData.getJSONObject("events");
            JSONArray objectArray = eventObject.getJSONArray("event");
            int nEvents = objectArray.length();

            //loop through the array and find the event the user clicked
            for (int i=0; i < nEvents; i++){
                JSONObject currentEventObject = objectArray.getJSONObject(i);
                String eventName = currentEventObject.getString("title");

                if(eventName.equals(eventList.getItemAtPosition(position).toString())){

                    //pop a toast with the event description
                    String eventDescription = currentEventObject.getString("description");
                    Toast.makeText(this, eventDescription, Toast.LENGTH_LONG).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class onListViewClickHandler implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //
            eventToast(JSONinput, position);
        }
    }
}
