package bit.dewahm1.passingusername;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button setUserNameButton = (Button) findViewById(R.id.btn_settings);
        setNewUsername setuser = new setNewUsername();
        setUserNameButton.setOnClickListener(setuser);
    }

    public class setNewUsername implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            //make a new intent
            Intent returnUsername = new Intent();
            //get the user input
            EditText usernameInput = (EditText) findViewById(R.id.textfield_username);
            String username = usernameInput.getText().toString();
            //put the user name into the intent
            returnUsername.putExtra("usernameResult", username);
            setResult(Activity.RESULT_OK, returnUsername);
            //kill the activity so the main activity will be restored
            finish();
        }
    }
}
