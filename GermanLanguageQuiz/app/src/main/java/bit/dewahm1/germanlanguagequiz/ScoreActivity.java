package bit.dewahm1.germanlanguagequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent quizIntent = getIntent();
        Bundle scoreData = quizIntent.getExtras();

        int score = scoreData.getInt("score");

        TextView scoreView = (TextView) findViewById(R.id.tv_Score);
        scoreView.setText(Integer.toString(score));
    }
}
