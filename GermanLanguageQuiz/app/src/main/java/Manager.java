import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hayden on 3/28/2016.
 */
public class Manager {

    ArrayList<Question> questionList;
    Random swap = new Random();

    public Manager() {

        questionList = new ArrayList();

        String[] ImageList = {
                "apple.jpg",
                "car.jpg",
                "tree.jpg",
                "duck.jpg",
                "house.jpg",
                "witch.jpg",
                "cow.jpg",
                "milk.jpg",
                "sheep.jpg",
                "street,jpg",
                "chair.jpg"
        };
        String[] articleList = {
                "Der",
                "Das",
                "Der",
                "Die",
                "Das",
                "Die",
                "Die",
                "Die",
                "Das",
                "Die",
                "Der",
        };

        for(int i = 0; i < ImageList.length; i++)
        {
            questionList.add(new Question(ImageList[i], articleList[i]));
        }

        for(int i = 0; i < 100; i++)
        {
            Question temp;

            int swap1 = swap.nextInt(questionList.size());
            int swap2 = swap.nextInt(questionList.size());

            temp = questionList.get(swap1);
            questionList.set(swap1, questionList.get(swap2));
            questionList.set(swap2, temp);
        }
    }
}
