package bit.dewahm1.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor accelerometer;
    ImageView ballImage;
    DisplayMetrics displayMetrics;
    float ballX;
    float ballY;
    int imageOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)MainActivity.this.getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        ballImage = (ImageView)findViewById(R.id.imageView_golfBall);
        ballImage.setImageResource(R.drawable.golf_ball);

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        ballX = 50;
        ballY = 50;
        imageOffset = 50;
    }

    public class accelerometerHandler implements SensorEventListener
    {
        @Override
        public void onSensorChanged(SensorEvent event)
        {
            float accelX = event.values[0];
            float accelY = event.values[1];
            float accelZ = event.values[2];

            ballX = ballImage.getX() - (accelX * 3);
            ballY = ballImage.getY() + (accelY * 3);

            checkBoundries();

            ballImage.setX(ballX);
            ballImage.setY(ballY);
        }

        public void checkBoundries()
        {
            if(ballX < 0)
                ballX = 0;
            if(ballY < 0)
                ballY = 0;
            if(ballX > displayMetrics.widthPixels)
                ballX = displayMetrics.widthPixels - imageOffset;
            if(ballY > displayMetrics.heightPixels)
                ballY = displayMetrics.heightPixels - imageOffset;
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
