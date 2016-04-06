package bit.dewahm1.citydatabase;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateDataBase();
    }

    public void CreateDataBase()
    {
        SQLiteDatabase cityDB;
        cityDB = openOrCreateDatabase("cityDB", MODE_PRIVATE, null);

        String creatQuery = "CREATE TABLE IF NOT EXISITS tblCity (" +
                            "cityID INTERGER PRIMARY KEY AUTOINCREMENT, " +
                            "cityName TEXT NOT NULL, " +
                            "countryName TEXT NOT NULL);";
        cityDB.execSQL(creatQuery);

        String record1 = "INSERT INTO tblCity VALUES(null,'Sydney', 'Australia')";
        String record2 = "INSERT INTO tblCity VALUES(null,'Brisbane', 'Australia')";
        String record3 = "INSERT INTO tblCity VALUES(null,'Melbourne', 'Australia')";
        String record4 = "INSERT INTO tblCity VALUES(null,'Auckland', 'New Zealand')";
        String record5 = "INSERT INTO tblCity VALUES(null,'Wellington', 'New Zealand')";
        String record6 = "INSERT INTO tblCity VALUES(null,'Dunedin', 'New Zealand')";
    }
}
