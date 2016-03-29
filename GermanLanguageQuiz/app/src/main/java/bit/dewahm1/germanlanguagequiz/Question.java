package bit.dewahm1.germanlanguagequiz;

import android.content.res.Resources;
import android.media.Image;

/**
 * Created by Hayden on 3/28/2016.
 */
public class Question {

    private int image;
    private String article;

    public Question(int image, String article)
    {
        this.setImage(image);
        this.setArticle(article);
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
