package bit.dewahm1.complexscreencontrols_spinner_p22t2;

import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Hayden on 3/22/2016.
 */
public class DialogBox extends DialogFragment {

    public DialogBox(){}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        YesButtonHandler yes = new YesButtonHandler();
        NoButtonHandler no = new NoButtonHandler();
        builder.setPositiveButton("Yes", yes);
        builder.setNegativeButton("No", no);

        Dialog customDialog = builder.create();

        return customDialog;
    }

    public class YesButtonHandler implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.DialogBoxConfirm(true);
        }
    }

    public class NoButtonHandler implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.DialogBoxConfirm(false);
        }
    }
}
