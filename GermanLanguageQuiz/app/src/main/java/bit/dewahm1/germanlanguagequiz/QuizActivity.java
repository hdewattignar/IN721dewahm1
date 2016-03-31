package bit.dewahm1.germanlanguagequiz;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class QuizActivity extends AppCompatActivity {

    Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //add action bar back button
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        // instantiate manager and call its setupGame method
        manager = new Manager();
        manager.setupGame();

        //display the first question
        newQuestion();
    }

    public void getResult(Boolean correctAnswer)
    {
        //if the answer was correct add 1 to the score
        if(correctAnswer)
            manager.score+=1;

        //display the next question
        newQuestion();
    }

    private void newQuestion() {

        //get the next question from the manager class
        Question nextQuestion = manager.getNextQuestion();

        //the manager.nextQuestion method will return a null question if there are no more questions
        if(nextQuestion != null) {

            //create a bundle and add the image reference and article of the current question
            Bundle newBundle = new Bundle();
            newBundle.putInt("image", nextQuestion.getImage());
            newBundle.putInt("currentQuestion", manager.questionNumber);
            newBundle.putInt("total", manager.totalQuestions);
            newBundle.putString("article", nextQuestion.getArticle());

            //create the fragment
            Fragment createFragment = new QuizFragment();
            createFragment.setArguments(newBundle);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, createFragment);
            ft.commit();
        }
        //if there are no more questions left go the the score activity
        else
            gotoScore();
    }

    private void gotoScore() {

        Intent changeToScoreActivity = new Intent(QuizActivity.this, ScoreActivity.class);
        changeToScoreActivity.putExtra("score", manager.score);
        changeToScoreActivity.putExtra("total", manager.questionNumber);
        startActivity(changeToScoreActivity);
    }

    //allows the back button in the actionbar to return to the main activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
