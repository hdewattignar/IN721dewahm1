package bit.dewahm1.languagepreference;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSet = (Button)findViewById(R.id.btn_set);
        SetLanguageClickHandler setClick = new SetLanguageClickHandler();
        btnSet.setOnClickListener(setClick);

        prefs = getSharedPreferences("prefsLanguage", MODE_PRIVATE);
        prefsEditor = prefs.edit();


        String languagePreference = prefs.getString("language", null);
        if(languagePreference != null)
        {
            String greetingInChosenLanguage = getGreeting(languagePreference);

            TextView txtLanguage = (TextView)findViewById(R.id.txt_languageHelloWorld);
            txtLanguage.setText(greetingInChosenLanguage);
        }

        String colourPreference = prefs.getString("colour", null);
        if(colourPreference != null)
        {
            int greetingInChosenColour = getColour(colourPreference);

            TextView txtLanguage = (TextView)findViewById(R.id.txt_languageHelloWorld);
            txtLanguage.setText(greetingInChosenColour);
        }
    }

    private String getGreeting(String language)
    {
        String greeting = "";

        switch(language)
        {
            case("French"):
                greeting = "Bonjour Le Monde";
                break;
            case("German"):
                greeting = "Hallo Welt";
                break;
            case("Spanish"):
                greeting = "Hola Mundo";
                break;
            default:
                break;
        }

        return greeting;
    }

    private int getColour(String colour)
    {
        int txtcolour = Color.BLACK;

        switch(colour)
        {
            case("Red"):
                txtcolour = Color.RED;
                break;
            case("Green"):
                txtcolour = Color.GREEN;
                break;
            case("Blue"):
                txtcolour = Color.BLUE;
                break;
            default:
                break;
        }

        return txtcolour;
    }

    public class SetLanguageClickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            RadioGroup languageRadioGroup = (RadioGroup) findViewById(R.id.rg_LanguageSelection);
            int checkedLanguageId = languageRadioGroup.getCheckedRadioButtonId();
            RadioButton checkedLanguageButton = (RadioButton)findViewById(checkedLanguageId);
            String checkedLanguage = checkedLanguageButton.getText().toString();

            RadioGroup colourRadioGroup = (RadioGroup) findViewById(R.id.rg_colours);
            int checkedColourId = colourRadioGroup.getCheckedRadioButtonId();
            RadioButton checkedColourButton = (RadioButton)findViewById(checkedColourId);
            String checkedColour = checkedColourButton.getText().toString();

            prefsEditor.putString("language", checkedLanguage);
            prefsEditor.putString("colour", checkedColour);
            prefsEditor.commit();

            TextView txtLanguage = (TextView)findViewById(R.id.txt_languageHelloWorld);
            txtLanguage.setText(getGreeting(checkedLanguage));
            txtLanguage.setTextColor(getColour(checkedColour));

        }

    }
}
