package bit.dewahm1.welcomedun_drawer;

import android.content.Intent;
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
        SetupListView();

        ListView navigationList = (ListView) findViewById(R.id.lv_selection);
        navigationList.setOnItemClickListener(new ListViewNavigation());

    }

    public void SetupListView()
    {
        String[] navigationCategories = {"Activities", "Shopping", "Dining", "Services"};

        ArrayAdapter<String> navigationAdapter = new ArrayAdapter<String>(this, R.layout.activity_activities, navigationCategories);

        ListView dunedinActivitiesListView = (ListView) findViewById(R.id.lv_selection);

        dunedinActivitiesListView.setAdapter(navigationAdapter);
    }

    public class ListViewNavigation implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String selectedOptionString = (String) parent.getItemAtPosition(position).toString();
            Intent goToIntent;


            switch(selectedOptionString){
                case "Activities":
                    goToIntent = new Intent(MainActivity.this, Activities.class);
                    break;
                case "Shopping":
                    goToIntent = new Intent(MainActivity.this, Shopping.class);
                    break;
                case "Dining":
                    goToIntent = new Intent(MainActivity.this, Dining.class);
                    break;
                case "Services":
                    goToIntent = new Intent(MainActivity.this, Services.class);
                    break;
                default:
                    goToIntent = new Intent(MainActivity.this, Services.class);
            }

            if (goToIntent != null)
                startActivity(goToIntent);





        }
    }


}
