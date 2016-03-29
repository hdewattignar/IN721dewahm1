package bit.dewahm1.germanlanguagequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startGameButton = (Button)findViewById(R.id.btn_MainStart);
        Button closeGameButton = (Button)findViewById(R.id.btn_MainExit);

        StartGameButtonHandler startGame = new StartGameButtonHandler();
        ExitGameButtonHandler exitGame = new ExitGameButtonHandler();

        startGameButton.setOnClickListener(startGame);
        closeGameButton.setOnClickListener(exitGame);
    }

    class StartGameButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Intent changeToQuestionActivity = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(changeToQuestionActivity);
        }
    }

    class ExitGameButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            System.exit(RESULT_OK);
        }
    }
}
