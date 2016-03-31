package bit.dewahm1.germanlanguagequiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Hayden on 3/29/2016.
 */
public class QuizFragment extends Fragment {

    RadioGroup radiogroupOptions;
    RadioButton selectedRadioButton;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstaceState)
    {
        //inflate the fragment layout
        View fragmantView = inflater.inflate(R.layout.quiz_fragment, container, false);

        //set the image to the image passed in by getArguments()
        ImageView fragmentImage = (ImageView) fragmantView.findViewById(R.id.image_QuestionImage);
        fragmentImage.setImageResource(getArguments().getInt("image"));

        //get the radio group
        radiogroupOptions = (RadioGroup) fragmantView.findViewById(R.id.rg_articleSelection);

        //wire up submit button
        Button submitButton = (Button) fragmantView.findViewById(R.id.btn_Submit);
        AnswerButtonHandler answer = new AnswerButtonHandler();
        submitButton.setOnClickListener(answer);

        //setup score display
        int currentQuestionNumber = getArguments().getInt("currentQuestion");
        int numQuestions = getArguments().getInt("total");
        TextView scoreDisplay = (TextView)fragmantView.findViewById(R.id.tv_runningTotal);
        scoreDisplay.setText(currentQuestionNumber + "/" + numQuestions);

        return fragmantView;
    }

    public boolean isAnswerCorrect()
    {
        Boolean correctAnswer;

        //get the selected radio button
        selectedRadioButton = (RadioButton) radiogroupOptions.findViewById(radiogroupOptions.getCheckedRadioButtonId());

        //check radio button text against the article passed in from getArguments()
        if(getArguments().getString("article").equals(selectedRadioButton.getText()))
        {
            correctAnswer = true;
        }
        else
        {
            correctAnswer = false;
        }

        return correctAnswer;
    }

    //pass back to question activity with result of question
    public void getNextQuestion()
    {
        QuizActivity quiz = (QuizActivity) getActivity();
        quiz.getResult(isAnswerCorrect());
    }

    //build the feedback dialog box fragment
    public class FeedBackDialogBox extends DialogFragment
    {
        public FeedBackDialogBox(){}

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            if(isAnswerCorrect() == true)
                builder.setTitle("Correct!");
            else
                builder.setTitle("Wrong");

            builder.setPositiveButton("Continue", new DialogButtonHandler());

            Dialog feedBack = builder.create();

            return feedBack;
        }
    }

    //Button handler for the sumbit button
    public class AnswerButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

<<<<<<< HEAD
            RadioGroup rg = (RadioGroup)getActivity().findViewById(R.id.rg_articleSelection);

            if (rg.getCheckedRadioButtonId() == -1) {

            }
            else
            {
                //shows the dialog fragment
                FeedBackDialog feedBackDialog = new FeedBackDialog();
                FragmentManager fm = getFragmentManager();
                feedBackDialog.show(fm, "continue");
            }

=======
            //shows the dialog fragment
            FeedBackDialogBox feedBackDialog = new FeedBackDialogBox();
            FragmentManager fm = getFragmentManager();

            feedBackDialog.setCancelable(false);
            feedBackDialog.show(fm, "continue");
>>>>>>> origin/master
        }
    }

    public class DialogButtonHandler implements DialogInterface.OnClickListener
    {


        @Override
        public void onClick(DialogInterface dialog, int which) {

            getNextQuestion();
            dialog.dismiss();

        }
    }
}
