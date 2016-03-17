package bit.dewahm1.requestdata;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //wire button
        Button changeTextColour = (Button) findViewById(R.id.btn_textcolour);
        ClickChangeColour clickbtn = new ClickChangeColour();
        changeTextColour.setOnClickListener(clickbtn);
    }

    public class ClickChangeColour implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            //go the the settings screen
            Intent settingsIntent = new Intent(MainActivity.this, Settings.class);
            startActivityForResult(settingsIntent,0);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if((requestCode == 0) && (resultCode == Activity.RESULT_OK)){

            // result is the new colour
            int result = data.getIntExtra("colourResult", 0);

            //set the text colour to the new text colour
            TextView usernameTextView = (TextView) findViewById(R.id.tv_mainscreen_main);
            usernameTextView.setTextColor(result);

        }
    }
}
