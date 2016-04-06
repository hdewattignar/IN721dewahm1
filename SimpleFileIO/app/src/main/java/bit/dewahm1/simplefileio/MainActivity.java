package bit.dewahm1.simplefileio;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnFillList = (Button)findViewById(R.id.btn_fillList);
        FillListButtonHandler fillListHandler = new FillListButtonHandler();
        btnFillList.setOnClickListener(fillListHandler);


    }

    public class FillListButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            ArrayList<String> cityNameArray = new ArrayList<String>();
            String assetFileName = "city_names.txt";

            try{

                AssetManager am = getAssets();
                InputStream is = am.open(assetFileName);
                InputStreamReader ir = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ir);

                String newCity;
                while((newCity = br.readLine()) != null)
                {
                    cityNameArray.add(newCity);
                }

                br.close();
            }
            catch (IOException e)
            {
                Toast toast = Toast.makeText(getApplicationContext(),"File not found", Toast.LENGTH_LONG);
            }

            ArrayAdapter<String> cityNamesAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,cityNameArray);

            ListView cityList = (ListView)findViewById(R.id.listView_CityList);
            cityList.setAdapter(cityNamesAdapter);
        }
    }
}
