package by.training.epam.lab9.bean.entity;

/**
 * Created by Aliaksandr_Harmaza on 10/25/2016.
 */
public class Question {

    private Long id;
    private String text;
    private Long testID;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTestID() {
        return testID;
    }

    public void setTestID(Long testID) {
        this.testID = testID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != null ? !id.equals(question.id) : question.id != null) return false;
        if (text != null ? !text.equals(question.text) : question.text != null) return false;
        return testID != null ? testID.equals(question.testID) : question.testID == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (testID != null ? testID.hashCode() : 0);
        return result;
    }
}
