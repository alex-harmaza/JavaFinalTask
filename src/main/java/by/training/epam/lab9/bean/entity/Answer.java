package by.training.epam.lab9.bean.entity;

/**
 * Created by Aliaksandr_Harmaza on 10/25/2016.
 */
public class Answer {

    private String text;
    private Long questionID;
    private Boolean isCorrect;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (text != null ? !text.equals(answer.text) : answer.text != null) return false;
        if (questionID != null ? !questionID.equals(answer.questionID) : answer.questionID != null) return false;
        return isCorrect != null ? isCorrect.equals(answer.isCorrect) : answer.isCorrect == null;

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (questionID != null ? questionID.hashCode() : 0);
        result = 31 * result + (isCorrect != null ? isCorrect.hashCode() : 0);
        return result;
    }
}
