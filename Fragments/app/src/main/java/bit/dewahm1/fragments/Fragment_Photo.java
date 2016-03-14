package bit.dewahm1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by dewahm1 on 15/03/2016.
 */
public class Fragment_Photo extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_photo_layout, container, false);

        return fragmentView;

    }

}
