package bit.dewahm1.citydatabase;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase cityDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateDataBase();
        populateSpinner();

        Spinner spinnerCountry = (Spinner)findViewById(R.id.spinner_CountryName);
        spinnerCountry.setOnItemSelectedListener(new CountryClickHandler());
    }

    public void CreateDataBase()
    {

        cityDB = openOrCreateDatabase("cityDB", MODE_PRIVATE, null);

        String dropQuery = "DROP TABLE tblCity";
        cityDB.execSQL(dropQuery);

        String createQuery = "CREATE TABLE IF NOT EXISTS tblCity (" +
                             "cityID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "cityName TEXT NOT NULL, " +
                             "countryName TEXT NOT NULL);";
        cityDB.execSQL(createQuery);

        String record1 = "INSERT INTO tblCity VALUES(null,'Sydney', 'Australia')";
        String record2 = "INSERT INTO tblCity VALUES(null,'Brisbane', 'Australia')";
        String record3 = "INSERT INTO tblCity VALUES(null,'Melbourne', 'Australia')";
        String record4 = "INSERT INTO tblCity VALUES(null,'Auckland', 'New Zealand')";
        String record5 = "INSERT INTO tblCity VALUES(null,'Wellington', 'New Zealand')";
        String record6 = "INSERT INTO tblCity VALUES(null,'Dunedin', 'New Zealand')";

        cityDB.execSQL(record1);
        cityDB.execSQL(record2);
        cityDB.execSQL(record3);
        cityDB.execSQL(record4);
        cityDB.execSQL(record5);
        cityDB.execSQL(record6);
    }

    public void populateSpinner()
    {
        String selectQuery = "SELECT DISTINCT countryName FROM tblCity";
        Cursor recordSet = cityDB.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();
        String[] displayStringArray = new String[recordCount];

        int countryNameIndex = recordSet.getColumnIndex("countryName");
        recordSet.moveToFirst();

        for(int r = 0; r < recordCount; r++)
        {
            String countryName = recordSet.getString(countryNameIndex);
            displayStringArray[r] = countryName;

            recordSet.moveToNext();
        }

        Spinner countrySpinner = (Spinner) findViewById(R.id.spinner_CountryName);
        int layoutID = android.R.layout.simple_spinner_item;
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(MainActivity.this, layoutID, displayStringArray);
        countrySpinner.setAdapter(countryAdapter);

    }

    public class CountryClickHandler implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ArrayList<String> cities = new ArrayList<String>();
            Spinner countrySpinner = (Spinner)findViewById(R.id.spinner_CountryName);
            String selectedText = countrySpinner.getSelectedItem().toString();
            cities = getCities(selectedText);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,cities);
            ListView citiesList = (ListView)findViewById(R.id.listView_SearchResults);
            citiesList.setAdapter(adapter);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private ArrayList<String> getCities(String selectedCountry) {

        ArrayList<String> cities = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT cityName FROM tblCity WHERE countryName = " + "\"" + selectedCountry + "\"";

        Cursor recordSet = cityDB.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();

        int cityNameIndex = recordSet.getColumnIndex("cityName");

        recordSet.moveToFirst();

        for(int r = 0; r < recordCount; r++){
            String cityName = recordSet.getString(cityNameIndex);
            cities.add(cityName);
            recordSet.moveToNext();
        }
        return cities;
    }
}
