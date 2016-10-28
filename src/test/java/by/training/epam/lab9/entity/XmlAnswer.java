package by.training.epam.lab9.entity;

/**
 * Created by alexh on 28.10.2016.
 */
public class XmlAnswer {

    private String text;
    private long questionID;
    private boolean isCorrect;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(long questionID) {
        this.questionID = questionID;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
