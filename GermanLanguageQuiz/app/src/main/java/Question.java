import android.media.Image;

/**
 * Created by Hayden on 3/28/2016.
 */
public class Question {

    private String image;
    private String article;

    public Question(String image, String article)
    {
        this.setImage(image);
        this.setArticle(article);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
