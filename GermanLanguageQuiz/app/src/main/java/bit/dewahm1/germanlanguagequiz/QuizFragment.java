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
        View fragmantView = inflater.inflate(R.layout.quiz_fragment, container, false);

        ImageView fragmentImage = (ImageView) fragmantView.findViewById(R.id.image_QuestionImage);
        fragmentImage.setImageResource(getArguments().getInt("image"));

        radiogroupOptions = (RadioGroup) fragmantView.findViewById(R.id.rg_articleSelection);


        Button submitButton = (Button) fragmantView.findViewById(R.id.btn_Submit);
        AnswerButtonHandler answer = new AnswerButtonHandler();
        submitButton.setOnClickListener(answer);

        return fragmantView;
    }

    public boolean isAnswerCorrect()
    {
        Boolean correctAnswer;

        selectedRadioButton = (RadioButton) radiogroupOptions.findViewById(radiogroupOptions.getCheckedRadioButtonId());
        //selectedRadioButton.findViewById(radiogroupOptions.getCheckedRadioButtonId());

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

    public void getNextQuestion()
    {
        QuizActivity quiz = (QuizActivity) getActivity();
        quiz.getResult(isAnswerCorrect());
    }

    public class FeedBackDialog extends DialogFragment
    {
        public FeedBackDialog(){}

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

    public class AnswerButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            FeedBackDialog feedBackDialog = new FeedBackDialog();
            FragmentManager fm = getFragmentManager();
            feedBackDialog.show(fm, "continue");
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
