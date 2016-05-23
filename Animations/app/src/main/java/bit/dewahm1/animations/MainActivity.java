package bit.dewahm1.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.easyandroidanimations.library.BounceAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageAnimate = (ImageView)findViewById(R.id.imageView);

        Button animateButton = (Button)findViewById(R.id.button);
        ButtonHandler handler = new ButtonHandler();
        animateButton.setOnClickListener(handler);
    }

    public class ButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            ImageView imageAnimate = (ImageView)findViewById(R.id.imageView);
            new BounceAnimation(imageAnimate).animate();
        }
    }
}
