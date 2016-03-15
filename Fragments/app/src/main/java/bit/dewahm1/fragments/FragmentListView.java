package bit.dewahm1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Hayden on 3/15/2016.
 */
public class FragmentListView extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.listview_fragment_layout, container, false);

        ListView lvcities = (ListView) fragmentView.findViewById(R.id.listView_fragment);

        String[] Cities = {"Amsterdam", "Berlin", "London", "Sydney", "Auckland", "Dublin", "Paris", "Rome", "Moscow"};
        ArrayAdapter<String> cityNameAdapter = new ArrayAdapter<>( getActivity(), R.layout.listview_layout, Cities);

        lvcities.setAdapter(cityNameAdapter);

        return fragmentView;
    }
}
