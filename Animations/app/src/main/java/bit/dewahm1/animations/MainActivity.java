package bit.dewahm1.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.easyandroidanimations.library.ExplodeAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button explodeButton = (Button)findViewById(R.id.button);
        ExplodeButtonHandler explodehandler = new ExplodeButtonHandler();
        explodeButton.setOnClickListener(explodehandler);

        Button standupButton = (Button)findViewById(R.id.button2);
        StandupButtonHandler standuphandler = new StandupButtonHandler();
        standupButton.setOnClickListener(standuphandler);
    }

    public class ExplodeButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            ImageView imageAnimate = (ImageView)findViewById(R.id.imageView);
            new ExplodeAnimation(imageAnimate).animate();
        }
    }

    public class StandupButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            ImageView imageAnimate = (ImageView)findViewById(R.id.imageView);
            imageAnimate.setImageResource(R.drawable.motorbike);
            YoYo.with(Techniques.StandUp)
                    .duration(700)
                    .playOn(findViewById(R.id.imageView));

        }
    }
}
