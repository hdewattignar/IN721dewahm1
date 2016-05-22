package bit.dewahm1.toolbar;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

     ImageView colourSquare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colourSquare = (ImageView)findViewById(R.id.imageView);
        colourSquare.setBackgroundColor(Color.GRAY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int itemID = item.getItemId();
        String itemTitle = (String)item.getTitle();

        colourSquare.setImageBitmap(null);
        int displayColour = Color.MAGENTA;

        switch(itemTitle)
        {
            case "Red":
                displayColour = Color.RED;
                break;
            case "Green":
                displayColour = Color.GREEN;
                break;
            case "Blue":
                displayColour = Color.BLUE;
                break;
        }

        colourSquare.setBackgroundColor(displayColour);

        return true;
    }
}
