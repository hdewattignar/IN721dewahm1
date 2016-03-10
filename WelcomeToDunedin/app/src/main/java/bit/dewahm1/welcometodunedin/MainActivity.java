package bit.dewahm1.welcometodunedin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] navigationCategories = {"Activities", "Shopping", "Dining", " Services"};

        ArrayAdapter<String> navigationAdapter = new ArrayAdapter<String>(this, R.layout.navigation_layout, navigationCategories);

        ListView dunedinActivitiesListView = (ListView) findViewById(R.id.listView_DunedinActivities);

        dunedinActivitiesListView.setAdapter(navigationAdapter);
    }

    public class ListViewNavigation implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        }
    }


}
