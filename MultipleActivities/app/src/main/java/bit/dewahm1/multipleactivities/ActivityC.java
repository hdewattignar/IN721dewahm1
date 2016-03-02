package bit.dewahm1.multipleactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_c);

        //get resource id
        Button btnChangeActivity = (Button) findViewById(R.id.btn_ActivityC);

        //instantiate class
        openWebsite open = new openWebsite();

        //
        btnChangeActivity.setOnClickListener(open);
    }

    class openWebsite implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            //create a Uri
            Uri goToGoogle = Uri.parse("https://www.google.co.nz/");

            //create an intent
            Intent goToGoogleIntent = new Intent(Intent.ACTION_VIEW,goToGoogle);

            //
            startActivity(goToGoogleIntent);

        }
    }
}
