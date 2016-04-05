package bit.dewahm1.welcometodunedin;

import android.graphics.drawable.Drawable;

/**
 * Created by dewahm1 on 1/04/2016.
 */
public class FunThing {

    Drawable activityImage;
    String activityName;

    public FunThing(Drawable activityImage, String activityName)
    {
        this.activityImage = activityImage;
        this.activityName = activityName;
    }

    @Override
    public String toString()
    {
        return activityName;
    }

}
