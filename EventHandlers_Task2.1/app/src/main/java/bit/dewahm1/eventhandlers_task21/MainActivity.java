package bit.dewahm1.eventhandlers_task21;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the reference to the the control
        Button ButtonClickMessage = (Button) findViewById(R.id.btnEvent);

        //Instantiate normal click and long click classes
        ButtonNormalClick normalclick = new ButtonNormalClick();
        ButtonLongClick longclick = new ButtonLongClick();

        //Invoke controls event setting methods
        ButtonClickMessage.setOnClickListener(normalclick);
        ButtonClickMessage.setOnLongClickListener(longclick);

    }

    //Normal click listener
    //Shows a message that says "Normal Click"
    class ButtonNormalClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Toast.makeText(MainActivity.this,"Normal Click",Toast.LENGTH_LONG).show();
        }
    }

    //Long click listener
    //Shows a message that says "Long Click"
    class ButtonLongClick implements View.OnLongClickListener
    {

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(MainActivity.this,"Long Click",Toast.LENGTH_LONG).show();
            return true;
        }
    }
}
