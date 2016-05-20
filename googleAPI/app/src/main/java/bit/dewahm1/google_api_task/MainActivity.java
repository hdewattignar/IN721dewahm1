package bit.dewahm1.google_api_task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    SupportMapFragment mapFragmant;
    GoogleMap mMap;
    LatLng DunedinLatLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double lat = -45.8788;
        double lon = 170.5028;
        DunedinLatLong = new LatLng(lat,lon);

        mapFragmant = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragmant.getMapAsync(new MapCallBackClass());
    }

    public class MapCallBackClass implements OnMapReadyCallback
    {
        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            mMap = googleMap;

            mMap.addMarker((new MarkerOptions().position(DunedinLatLong)));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(DunedinLatLong));
        }
    }
}
