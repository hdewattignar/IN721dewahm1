package bit.dewahm1.passingusername;

import android.app.Activity;
import android.content.Intent;
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

        Button goToSettings = (Button) findViewById(R.id.btn_settings);
        ClickGoToSettings clickSettings = new ClickGoToSettings();
        goToSettings.setOnClickListener(clickSettings);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if((requestCode == 0) && (resultCode == Activity.RESULT_OK)){

            String result = data.getStringExtra("usernameResult");

            TextView usernameTextView = (TextView) findViewById(R.id.txt_username);
            usernameTextView.setText(result);

        }
    }

    public class ClickGoToSettings implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);

            startActivityForResult(settingsIntent,0);
        }
    }
}
