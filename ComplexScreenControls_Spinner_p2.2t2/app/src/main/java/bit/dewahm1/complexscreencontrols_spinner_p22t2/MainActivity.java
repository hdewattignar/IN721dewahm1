package bit.dewahm1.complexscreencontrols_spinner_p22t2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the reference to the controls
        Button InstrumentSelected = (Button) findViewById(R.id.btnEnroll);

        //instantiate classes
        InstrumentSelectedAction instrumentAction = new InstrumentSelectedAction();

        //invoke controls event setting methods
        InstrumentSelected.setOnClickListener(instrumentAction);

        //populate spinner
        PopulateSpinner();
    }

    public void PopulateSpinner(){

        //array to go of months to go into the spinner
        String[] months =
                {
                        "January",
                        "Febuary",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December"
                };

        //get reference to the spinner
        Spinner monthSpinner = (Spinner) findViewById(R.id.spMonths);

        //get the id for the layout of a spinner
        int layoutID = android.R.layout.simple_spinner_item;

        //create adapater for the spinner
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(MainActivity.this, layoutID, months);
        monthSpinner.setAdapter(monthAdapter);
    }

    class InstrumentSelectedAction implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            //get reference to the radio group
            RadioGroup InstrumentGroup = (RadioGroup) findViewById(R.id.rgInstrumentSelection);
            Spinner selectedMonth = (Spinner) findViewById(R.id.spMonths);

            //get the selected radio button
            RadioButton chosenInstrument = (RadioButton) findViewById(InstrumentGroup.getCheckedRadioButtonId());

            //get the text from the radio button
            String chosenInstrmentText = chosenInstrument.getText().toString();

            // get the text from the selected spinner option
            String monthText = selectedMonth.getSelectedItem().toString();

            //feed back string to be printed
            String instrumentDisplayString = "You Have Enroled For " + chosenInstrmentText + " Lessons In " + monthText;

            //set the set in the text view
            TextView txtDisplayInstumentFeedback = (TextView) findViewById(R.id.txtSubmitMessage);
            txtDisplayInstumentFeedback.setText(instrumentDisplayString);
        }
    }
}
