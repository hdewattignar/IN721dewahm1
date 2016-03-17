package bit.dewahm1.requestdata;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //get the text view
        TextView settingsTextView = (TextView) findViewById(R.id.tv_settingsText);

        //make an intent. get the text colour and pass it back
        Intent returnColour = new Intent();
        int textColour = settingsTextView.getCurrentTextColor();
        returnColour.putExtra("colourResult", textColour);
        setResult(Activity.RESULT_OK, returnColour);
        finish();
    }
}
