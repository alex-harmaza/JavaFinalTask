package by.training.epam.lab9.entity;

/**
 * Created by alexh on 28.10.2016.
 */
public class XmlQuestion {

    private long id;
    private String text;
    private long testID;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTestID() {
        return testID;
    }

    public void setTestID(long testID) {
        this.testID = testID;
    }
}
