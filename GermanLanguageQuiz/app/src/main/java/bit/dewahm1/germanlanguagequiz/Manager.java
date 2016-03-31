package bit.dewahm1.germanlanguagequiz;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hayden on 3/28/2016.
 */
public class Manager {

    ArrayList<Question> questionList;
    Random swap = new Random();
    int score;
    int questionNumber;
    int totalQuestions;

    public Manager() {

        questionList = new ArrayList();
        score = 0;
        questionNumber = 0;
    }

    public void setupGame()
    {
        score = 0;
        questionNumber = 0;

        questionList.add(new Question(R.drawable.apple, "Der"));
        questionList.add(new Question(R.drawable.car, "Das"));
        questionList.add(new Question(R.drawable.tree, "Der"));
        questionList.add(new Question(R.drawable.duck, "Die"));
        questionList.add(new Question(R.drawable.house, "Das"));
        questionList.add(new Question(R.drawable.witch, "Die"));
        questionList.add(new Question(R.drawable.cow, "Die"));
        questionList.add(new Question(R.drawable.milk, "Die"));
        questionList.add(new Question(R.drawable.sheep, "Das"));
        questionList.add(new Question(R.drawable.street, "Die"));
        questionList.add(new Question(R.drawable.chair, "Der"));

        for(int i = 0; i < 100; i++)
        {
            Question temp;

            int swap1 = swap.nextInt(questionList.size());
            int swap2 = swap.nextInt(questionList.size());

            temp = questionList.get(swap1);
            questionList.set(swap1, questionList.get(swap2));
            questionList.set(swap2, temp);
        }

        totalQuestions = questionList.size();
    }

    public Question getNextQuestion()
    {
        Question nextQuestion;

        if(questionNumber < questionList.size())
        {
            nextQuestion = questionList.get(questionNumber);
            questionNumber++;
            return nextQuestion;
        }
        else
        {
            nextQuestion = null;
            return nextQuestion;
        }
    }
}
