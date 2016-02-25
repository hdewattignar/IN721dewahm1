package bit.dewahm1.usernamespecchar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        EditText specChar = (EditText) findViewById(R.id.editText_SpecChar);

        //
        CheckUserName namecheck = new CheckUserName();

        //
        specChar.setOnKeyListener(namecheck);

    }

    class CheckUserName implements View.OnKeyListener
    {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
            int viewID = v.getId();
            EditText usernameInput = (EditText)findViewById(viewID);
            String username = usernameInput.getText().toString();

            if (keyCode == KeyEvent.KEYCODE_ENTER)
            {
                if(username.length() == 8){
                    Toast.makeText(MainActivity.this,"Thank you" + username,Toast.LENGTH_LONG).show();
                }

                else
                    Toast.makeText(MainActivity.this,"Username must be 8 characters, " + username, Toast.LENGTH_LONG).show();
                return true;

            }
            return false;
        }
    }

}
