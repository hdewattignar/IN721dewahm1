package bit.dewahm1.welcometodunedin;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ThingsToDo extends AppCompatActivity {

    FunThing[] funThingArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funthingstodo);

        SetupFunThingArray();

        ActivitiesArrayAdapter customArrayAdapter = new ActivitiesArrayAdapter(this, R.layout.icon_layout,funThingArray);

        ListView lvFunThings = (ListView) findViewById(R.id.lv_Fun_Things);
        lvFunThings.setAdapter(customArrayAdapter);
    }

    private void SetupFunThingArray() {

        Resources resourceMachine = getResources();

        funThingArray = new FunThing[10];

        funThingArray[0] = new FunThing(resourceMachine.getDrawable(R.drawable.larnach_castle,null), "Larnach Castle");
        funThingArray[1] = new FunThing(resourceMachine.getDrawable(R.drawable.moana_pool,null), "Moana Pool");
        funThingArray[2] = new FunThing(resourceMachine.getDrawable(R.drawable.monarch,null), "Monarch");
        funThingArray[3] = new FunThing(resourceMachine.getDrawable(R.drawable.library,null), "Dunedin Library");
        funThingArray[4] = new FunThing(resourceMachine.getDrawable(R.drawable.olveston,null), "Olveston");
        funThingArray[5] = new FunThing(resourceMachine.getDrawable(R.drawable.op,null), "Otago Polytechnic");
        funThingArray[6] = new FunThing(resourceMachine.getDrawable(R.drawable.peninsula,null), "Otago Peninsula");
        funThingArray[7] = new FunThing(resourceMachine.getDrawable(R.drawable.salt_water_pool,null), "Salt Water Pool");
        funThingArray[8] = new FunThing(resourceMachine.getDrawable(R.drawable.speights_brewery,null), "Speights Brewery");
        funThingArray[9] = new FunThing(resourceMachine.getDrawable(R.drawable.taeri_gorge_railway,null), "Taeri Gorge");

    }

    public class ActivitiesArrayAdapter extends ArrayAdapter<FunThing> {
        public ActivitiesArrayAdapter(Context context, int resource, FunThing[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container)
        {
            LayoutInflater inflater = LayoutInflater.from(ThingsToDo.this);

            View customListIcon = inflater.inflate(R.layout.icon_layout, container, false);

            ImageView iconImageView = (ImageView) customListIcon.findViewById(R.id.iv_iconImage);
            TextView iconTextView = (TextView) customListIcon.findViewById(R.id.tv_iconText);

            FunThing currentItem = getItem(position);

            iconImageView.setImageDrawable(currentItem.activityImage);
            iconTextView.setText(currentItem.activityName);

            return customListIcon;
        }
    }
}
