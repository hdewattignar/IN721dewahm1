package bit.dewahm1.complexresources_task12b;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtFebFridays = (TextView)findViewById(R.id.TextFebFridays);

        Resources resourceResolver = getResources();
        int datesArray[] = resourceResolver.getIntArray((R.array.FebFridays));

        String txtToPrint = "Feburary Fridays are on: ";

        for (int i = 0; i < datesArray.length; i++){

            txtToPrint += datesArray[i] + ", ";
        }

        txtFebFridays.setText(txtToPrint);
    }
}
