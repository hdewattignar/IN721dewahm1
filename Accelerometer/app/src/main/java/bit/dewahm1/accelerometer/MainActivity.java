package bit.dewahm1.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor accelerometer;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)MainActivity.this.getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        image = (ImageView)findViewById(R.id.imageView);
        image.setImageResource(R.drawable.golf_ball);
    }

    public class accelerometerHandler implements SensorEventListener
    {
        @Override
        public void onSensorChanged(SensorEvent event)
        {
            float accelX = event.values[0];
            float accelY = event.values[1];
            float accelZ = event.values[2];

            float x = image.getX() - (accelX * 3);
            float y = (image.getY() + accelY) * 3;

            image.setX(x);
            image.setY(y);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(new accelerometerHandler(), accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(new accelerometerHandler());
    }
}
