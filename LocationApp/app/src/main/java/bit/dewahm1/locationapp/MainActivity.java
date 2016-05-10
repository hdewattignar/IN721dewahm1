package bit.dewahm1.locationapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.Random;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getLocation = (Button)findViewById(R.id.btn_location);
        LocationButtonHandler locationButtonHandler = new LocationButtonHandler();
        getLocation.setOnClickListener(locationButtonHandler);
    }

    public class LocationButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            //location manager
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            String locationProvider = LocationManager.NETWORK_PROVIDER;

            CurrentLocationListener locationListener = new CurrentLocationListener();

            //permission checks
            if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            //update
            locationManager.requestLocationUpdates(locationProvider, 1000, 1, locationListener);

            Location location = locationManager.getLastKnownLocation(locationProvider);

            getLocation(location);
        }
    }

    public class CurrentLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location) {
            getLocation(location);

            //execute async thread
            getCityAsync APIThread = new getCityAsync(MainActivity.this);
            APIThread.execute();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    public void getLocation(Location location) {

        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    public void setDisplay(Bitmap image, String city)
    {
        TextView longitudeText = (TextView)findViewById(R.id.txt_longitude);
        TextView latitudeText = (TextView)findViewById(R.id.txt_latitude);
        ImageView cityImage = (ImageView)findViewById(R.id.imageView);
        TextView cityText = (TextView)findViewById(R.id.txt_closestCity);

        //display the city name
        cityText.setText(city);

        //display the image
        if(image != null)
        {
            cityImage.setImageBitmap(image);//use the image from flickr
        }
        else
        {
            cityImage.setImageResource(R.drawable.notfound);// use the default
        }

        //set the text in the text views
        longitudeText.setText(Double.toString(longitude));
        latitudeText.setText(Double.toString(latitude));
    }

    public String setCity(String json)
    {
        String city = null;
        try{
            // get the city name from the json data
            JSONObject locationData = new JSONObject(json);
            city = locationData.getString("geoplugin_place");
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return city;
    }

    class getCityAsync extends AsyncTask<Void,Void,String>
    {
        String imageURL;
        Bitmap image;

        ProgressDialog progressDialog;

        public getCityAsync(MainActivity activity)
        {
            //instantiate progress dialog
            progressDialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute()
        {
            //show progress dialog
            progressDialog.setMessage("Getting your Location");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String JSON = null;

            while(getCity(JSON) == null) {

                String urlString = "http://www.geoplugin.net/extras/location.gp?" +
                        "lat=" + latitude +
                        "&long=" + longitude +
                        "&format=json";

                JSON = getJSONfromURL(urlString);

                imageURL = getImageURL(getCity(JSON));
            }

            if(imageURL != null)
            {
                image = getImage(imageURL);
            }

            return JSON;
        }

        @Override
        protected void onPostExecute(String fetchedString)
        {
            if(progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }

            setDisplay(image, setCity(fetchedString));
        }
    }

    public String getCity(String json)
    {
        String city = null;

        try{
            if(json == null)
                city = null;
            else
            {
                JSONObject cityObj = new JSONObject(json);

                city = cityObj.optString("geoplugin_place");
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
            city = null;
        }

        return city;
    }

    public String getJSONfromURL(String url)
    {
        String json = null;

        try{
            URL URLobject = new URL(url); //can throw malformed exception

            HttpURLConnection connection = (HttpURLConnection)URLobject.openConnection(); //can throw IO exception

            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String responseString;
            StringBuilder stringBuilder = new StringBuilder();

            while((responseString = bufferedReader.readLine()) != null)
            {
                stringBuilder = stringBuilder.append(responseString);
            }

            json = stringBuilder.toString();

            if(json.equals("[[]]"))
            {
                return null;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return  json;
    }

    public String getImageURL(String city)
    {
        String imageURL = null;

        String flickr = "https://api.flickr.com/services/rest/?" +
                "method=flickr.photos.search" +
                "&api_key=1ea787f450e9b9b35ab211f8ca1a4dcd" +
                "&tags=" + city +
                "&format=json" +
                "&nojsoncallback=1";

        flickr = getJSONfromURL(flickr);

        try{
            JSONObject data = new JSONObject(flickr);

            JSONObject obj = data.getJSONObject("photos");

            JSONArray array = obj.getJSONArray("photo");

            if(array.length() > 0)
            {
                JSONObject pic = array.getJSONObject(0);

                String farm = pic.getString("farm");
                String server = pic.getString("server");
                String id = pic.getString("id");
                String secret = pic.getString("farm");

                imageURL = "https://farm" + farm +
                        ".staticflickr.com/" + server +
                        "/" + id + "_" + secret +
                        "_m.jpg";
            }

            else
            {
                imageURL = null;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return imageURL;
    }

    public Bitmap getImage(String url)
    {
        Bitmap image = null;

        try{
            URL URLObject = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
            connection.connect();

            int responseCode = connection.getResponseCode();

            if(responseCode == 200)
            {
                InputStream inputStream = connection.getInputStream();
                image = BitmapFactory.decodeStream(inputStream);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return image;
    }
}
