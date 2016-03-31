package bit.dewahm1.germanlanguagequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //wire button handler. goes back to the main activity
        Button BackToStart = (Button)findViewById(R.id.btn_backtomain);
        GoBackToStart backtoStartHandler = new GoBackToStart();
        BackToStart.setOnClickListener(backtoStartHandler);

        //gets the score
        Intent quizIntent = getIntent();
        Bundle scoreData = quizIntent.getExtras();

        //set the score in the display
        int score = scoreData.getInt("score");
        int total = scoreData.getInt("total");
        TextView scoreView = (TextView) findViewById(R.id.tv_Score);
        scoreView.setText(Integer.toString(score) + "/" + total);
    }

    public class GoBackToStart implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent changeToMainActivity = new Intent(ScoreActivity.this, MainActivity.class);
            startActivity(changeToMainActivity);
        }
    }
}
