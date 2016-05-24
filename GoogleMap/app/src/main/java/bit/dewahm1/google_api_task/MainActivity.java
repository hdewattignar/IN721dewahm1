package bit.dewahm1.google_api_task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    double latitude;
    double longitude;
    int maxLat = 90;
    int maxLong = 180;

    SupportMapFragment mapFragment;
    GoogleMap mMap;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTele = (Button) findViewById(R.id.button);
        btnTele.setOnClickListener(new teleportClickHandler());

    }

    public class MapCallBackClass implements OnMapReadyCallback
    {
        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            mMap = googleMap;

            //add the marker to the map
            mMap.addMarker((new MarkerOptions().position(latLng)));
            //center the marker in the map
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    public void inflateMap(){
        //Inflate the fragment
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //randomly set the lat and long
        latitude = setLatOrLong(maxLat);
        longitude = setLatOrLong(maxLong);
        latLng = new LatLng(latitude,longitude);

        mapFragment.getMapAsync(new MapCallBackClass());
    }

    //randomly generate a latitude or longitude
    private double setLatOrLong(int max){
        Random rnd = new Random();

        //add two random numbers together and subtract the max value
        //to get a value that will include negatives
        int value = (rnd.nextInt(max) + rnd.nextInt(max)) - max;

        return value;
    }

    public class teleportClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            inflateMap();
        }
    }
}

