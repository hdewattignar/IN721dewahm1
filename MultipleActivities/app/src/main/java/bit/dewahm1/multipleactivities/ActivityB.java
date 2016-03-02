package bit.dewahm1.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_b);

        //get resource id
        Button btnChangeActivity = (Button) findViewById(R.id.btn_ActivityB);

        //instantiate class
        ChangeActivityC changeToActivityC = new ChangeActivityC();

        //
        btnChangeActivity.setOnClickListener(changeToActivityC);
    }




    class ChangeActivityC implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            Intent ChangeActivityIntent = new Intent(ActivityB.this, ActivityC.class);
            startActivity(ChangeActivityIntent);

        }
    }
}
