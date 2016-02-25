package bit.dewahm1.edittext;

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

        // get reference to the control
        EditText editTextNoAt = (EditText) findViewById(R.id.editTextNoAt);

        // instantiate 'textNoAt' class
        TextNoAt noAtMessage = new TextNoAt();

        //invoke controls event setting methods
        editTextNoAt.setOnKeyListener(noAtMessage);

    }

    //Check if the "@" symbol has been pressed
    //show a message if it has
    class TextNoAt implements View.OnKeyListener
    {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if(keyCode == KeyEvent.KEYCODE_AT)
            {
                Toast.makeText(MainActivity.this,"No @ Symbols", Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }
}
