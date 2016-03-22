package bit.dewahm1.complexscreencontrols_spinner_p22t2;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DialogBox confirmEnrolment;

    String chosenInstrmentText;
    String monthText;

    RadioGroup InstrumentGroup;
    Spinner selectedMonth;
    RadioButton chosenInstrument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the reference to the controls
        Button InstrumentSelected = (Button) findViewById(R.id.btnEnroll);

        //instantiate classes
        DialogButtonHandler instrumentAction = new DialogButtonHandler();

        //invoke controls event setting methods
        InstrumentSelected.setOnClickListener(instrumentAction);

        //populate spinner
        PopulateSpinner();



    }

    public void DialogBoxConfirm(boolean enrolConfirm)
    {
        confirmEnrolment.dismiss();

        //get reference to the radio group
        InstrumentGroup = (RadioGroup) findViewById(R.id.rgInstrumentSelection);
        selectedMonth = (Spinner) findViewById(R.id.spMonths);

        //get the selected radio button
        chosenInstrument = (RadioButton) findViewById(InstrumentGroup.getCheckedRadioButtonId());

        //get the text from the radio button
        chosenInstrmentText = chosenInstrument.getText().toString();

        // get the text from the selected spinner option
        monthText = selectedMonth.getSelectedItem().toString();

        if(enrolConfirm)
        {
            //feed back string to be printed
            String instrumentDisplayString = "You Have Enroled For" + chosenInstrmentText + " Lessons In " + monthText;

            //set the set in the text view
            TextView txtDisplayInstumentFeedback = (TextView) findViewById(R.id.txtSubmitMessage);
            txtDisplayInstumentFeedback.setText(instrumentDisplayString);
        }
        else
        {
            //feed back string to be printed
            String instrumentDisplayString = "Oh well...";

            //set the set in the text view
            TextView txtDisplayInstumentFeedback = (TextView) findViewById(R.id.txtSubmitMessage);
            txtDisplayInstumentFeedback.setText(instrumentDisplayString);
        }

    }

    public class DialogButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            confirmEnrolment = new DialogBox();
            confirmEnrolment.show(fm, "confirm");
        }
/*
        public void DialogBoxConfirm(boolean enrolConfirm)
        {
            confirmEnrolment.dismiss();

            //get the text from the radio button
            chosenInstrmentText = chosenInstrument.getText().toString();

            // get the text from the selected spinner option
            monthText = selectedMonth.getSelectedItem().toString();

            if(enrolConfirm)
            {
                //feed back string to be printed
                String instrumentDisplayString = "You Have Enroled For " + chosenInstrmentText + " Lessons In " + monthText;

                //set the set in the text view
                TextView txtDisplayInstumentFeedback = (TextView) findViewById(R.id.txtSubmitMessage);
                txtDisplayInstumentFeedback.setText(instrumentDisplayString);
            }
            else
            {
                //feed back string to be printed
                String instrumentDisplayString = "Oh well...";

                //set the set in the text view
                TextView txtDisplayInstumentFeedback = (TextView) findViewById(R.id.txtSubmitMessage);
                txtDisplayInstumentFeedback.setText(instrumentDisplayString);
            }

        }
        */
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
}
