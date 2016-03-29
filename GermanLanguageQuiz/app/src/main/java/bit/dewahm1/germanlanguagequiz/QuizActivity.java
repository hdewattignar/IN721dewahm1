package bit.dewahm1.germanlanguagequiz;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuizActivity extends AppCompatActivity {

    Manager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // instantiate manager and call its setupGame method
        manager = new Manager();
        manager.setupGame();

        newQuestion();
    }

    public void getResult(Boolean correctAnswer)
    {
        if(correctAnswer)
            manager.score+=1;

        newQuestion();
    }

    private void newQuestion() {

        Question nextQuestion = manager.getNextQuestion();

        if(nextQuestion != null) {
            Bundle newBundle = new Bundle();
            newBundle.putInt("image", nextQuestion.getImage());
            newBundle.putString("article", nextQuestion.getArticle());
            Fragment createFragment = new QuizFragment();
            createFragment.setArguments(newBundle);
            FragmentManager fm = getFragmentManager();


            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, createFragment);
            ft.commit();
        }
        else
            gotoScore();
    }

    private void gotoScore() {

        Intent changeToScoreActivity = new Intent(QuizActivity.this, ScoreActivity.class);
        changeToScoreActivity.putExtra("score", manager.score);
        startActivity(changeToScoreActivity);
    }
}
