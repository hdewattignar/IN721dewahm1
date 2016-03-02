package bit.dewahm1.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get resource id
        Button btnChangeActivity = (Button) findViewById(R.id.btn_ActivityA);

        //instantiate class
        ChangeActivityB changeToActivityB = new ChangeActivityB();

        //
        btnChangeActivity.setOnClickListener(changeToActivityB);

    }

    class ChangeActivityB implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            Intent ChangeActivityIntent = new Intent(MainActivity.this, ActivityB.class);
            startActivity(ChangeActivityIntent);

        }
    }
}
