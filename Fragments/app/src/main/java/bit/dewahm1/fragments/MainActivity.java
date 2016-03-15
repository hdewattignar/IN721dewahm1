package bit.dewahm1.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablet_layout);

        //get button resource
        Button showImage = (Button) findViewById(R.id.btn_showPhoto_tablet);
        Button showListView = (Button) findViewById(R.id.btn_showPhoto_tablet);
        //create an instance of the onclicklistener
        ClickShowImage clickshowimage = new ClickShowImage();
        ClickShowListView clickshowlistview = new ClickShowListView();
        //set the buttons onclick
        showImage.setOnClickListener(clickshowimage);
        showListView.setOnClickListener(clickshowlistview);
    }

    class ClickShowImage implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Fragment dynamicFragment = new Fragment_Photo();
            FragmentManager fm = getFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.ll_showImgPlaceholder, dynamicFragment);
            ft.commit();

        }
    }

    class ClickShowListView implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Fragment dynamicFragment = new FragmentListView();
            FragmentManager fm = getFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.ll_showListPlaceholder, dynamicFragment);
            ft.commit();

        }
    }
}
