package bit.dewahm1.complexscreencontrols_p22t1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //get the reference to the controls
        Button InstrumentSelected = (Button) findViewById(R.id.btnAction);


        //instantiate classes
        InstrumentSelectedAction instrumentAction = new InstrumentSelectedAction();


        //invoke controls event setting methods
        InstrumentSelected.setOnClickListener(instrumentAction);
    }

    //Button click listener
    //gets the radio button that is selected from the radio group and shows the appropriate message
    class InstrumentSelectedAction implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            //get reference to the radio group
            RadioGroup InstrumentGroup = (RadioGroup) findViewById(R.id.rgInstruments);

            //get the selected radio button
            RadioButton chosenInstrument = (RadioButton) findViewById(InstrumentGroup.getCheckedRadioButtonId());

            //get the text from the button
            String chosenInstrmentText = chosenInstrument.getText().toString();

            //feed back string to be printed
            String instrumentDisplayString = "You Have Enroled For " + chosenInstrmentText + " Lessons";

            //set the set in the tect view
            TextView txtDisplayInstumentFeedback = (TextView) findViewById(R.id.txtSelectedInstrument);
            txtDisplayInstumentFeedback.setText(instrumentDisplayString);
        }
    }
}
